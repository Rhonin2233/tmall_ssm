package com.rhonin.myssm.controller;

import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.pojo.Product;
import com.rhonin.myssm.pojo.Property;
import com.rhonin.myssm.pojo.PropertyValue;
import com.rhonin.myssm.service.CategoryService;
import com.rhonin.myssm.service.ProductService;
import com.rhonin.myssm.service.PropertyService;
import com.rhonin.myssm.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("/editPropertyValue")
    public String list(Model model, int product_id) {
        Product product = productService.findById(product_id);
        Category category = categoryService.findById(product.getCategory_id());
        List<PropertyValue> propertyValues = propertyValueService.list(product_id);
        if (null != propertyValues && !propertyValues.isEmpty()) {
            for (PropertyValue p : propertyValues) {
                p.setProperty(propertyService.findById(p.getProperty_id()));
            }
        } else {
            List<Property> properties = propertyService.list(category.getId());
            for (Property pr : properties) {
                PropertyValue propertyValue = new PropertyValue(product_id, pr.getId(), "待补充");
                propertyValueService.add(propertyValue);
            }
            propertyValues = propertyValueService.list(product_id);
            for (PropertyValue p : propertyValues) {
                p.setProperty(propertyService.findById(p.getProperty_id()));
            }
        }
        model.addAttribute("p", product);
        model.addAttribute("c", category);
        model.addAttribute("pv", propertyValues);
        return "admin/editPropertyValue";
    }

    @RequestMapping("/updatePropertyValue")
    @ResponseBody
    public Boolean update(PropertyValue propertyValue) {
        int line = propertyValueService.update(propertyValue);
        if (line == 1) {
            return true;
        } else {
            return false;
        }
    }
}
