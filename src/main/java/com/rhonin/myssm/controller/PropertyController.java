package com.rhonin.myssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.pojo.Property;
import com.rhonin.myssm.service.CategoryService;
import com.rhonin.myssm.service.PropertyService;
import com.rhonin.myssm.service.PropertyValueService;
import com.rhonin.myssm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyValueService propertyValueService;

    @RequestMapping("/listProperty")
    public String list(Model model, Page page, int category_id) {
        page.setParam("&category_id=" + category_id);
        Category category = categoryService.findById(category_id);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> properties = propertyService.list(category_id);
        page.setTotal((int) new PageInfo<>(properties).getTotal());
        model.addAttribute("properties", properties);
        model.addAttribute("c", category);
        return "admin/listProperty";
    }

    @RequestMapping("/addProperty")
    public String add(Property property) {
        propertyService.add(property);
        return "redirect:listProperty?category_id=" + property.getCategory_id();
    }

    @RequestMapping("/deleteProperty")
    public String delete(Property property) {
        propertyValueService.deletebyPropertyId(property.getId());
        propertyService.delete(property.getId());
        return "redirect:listProperty?category_id=" + property.getCategory_id();
    }

    @RequestMapping("/editProperty")
    public String edit(Model model, int id) {
        Property property = propertyService.findById(id);
        Category category = categoryService.findById(property.getCategory_id());
        model.addAttribute("p", property);
        model.addAttribute("c", category);
        return "admin/editProperty";
    }

    @RequestMapping("/updateProperty")
    public String update(Property property) {
        propertyService.update(property);
        return "redirect:listProperty?category_id=" + property.getCategory_id();
    }
}
