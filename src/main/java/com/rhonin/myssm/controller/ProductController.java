package com.rhonin.myssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.pojo.Product;
import com.rhonin.myssm.pojo.Product;
import com.rhonin.myssm.pojo.ProductImage;
import com.rhonin.myssm.service.*;
import com.rhonin.myssm.service.ProductService;
import com.rhonin.myssm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;

    @RequestMapping("/listProduct")
    public String list(Model model, Page page, int category_id) {
        page.setParam("&category_id=" + category_id);
        Category category = categoryService.findById(category_id);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> products = productService.list(category_id);
        for (Product p : products) {
            List<ProductImage> productImages = productImageService.listSingle(p.getId());
            if (null != productImages && !productImages.isEmpty()) {
                p.setFirstImage(productImages.get(0));
            }
        }
        page.setTotal((int) new PageInfo<>(products).getTotal());
        model.addAttribute("products", products);
        model.addAttribute("c", category);
        return "admin/listProduct";
    }

    @RequestMapping("/addProduct")
    public String add(Product product) throws ParseException {
        String s = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        product.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(s));
        productService.add(product);
        return "redirect:listProduct?category_id=" + product.getCategory_id();
    }

    @RequestMapping("/deleteProduct")
    public String delete(HttpServletRequest request, Product product) {
        List<ProductImage> productSingleImages = productImageService.listSingle(product.getId());
        List<ProductImage> productDetailImage = productImageService.listDetail(product.getId());
        if (productSingleImages != null && !productSingleImages.isEmpty()) {
            for (ProductImage ps : productSingleImages) {
                File imageFolder = new File(request.getServletContext().getRealPath("img/productSingle"), ps.getId() + ".jpg");
                imageFolder.delete();
            }
        }
        if (productDetailImage != null && !productDetailImage.isEmpty()) {
            for (ProductImage pd : productDetailImage) {
                File imageFolder = new File(request.getServletContext().getRealPath("img/productSingle"), pd.getId() + ".jpg");
                imageFolder.delete();
            }
        }
        propertyValueService.deleteByProductId(product.getId());
        productImageService.deleteByPid(product.getId());
        productService.delete(product.getId());
        return "redirect:listProduct?category_id=" + product.getCategory_id();
    }

    @RequestMapping("/editProduct")
    public String edit(Model model, int id) {
        Product product = productService.findById(id);
        Category category = categoryService.findById(product.getCategory_id());
        model.addAttribute("c", category);
        model.addAttribute("p", product);
        return "admin/editProduct";
    }

    @RequestMapping("/updateProduct")
    public String update(Product product) {
        productService.update(product);
        return "redirect:listProduct?category_id=" + product.getCategory_id();
    }

    @RequestMapping("/checkProduct")
    @ResponseBody
    public Boolean check(int category_id) {
        List<Product> products = productService.list(category_id);
        System.out.println(category_id);
        return null != products && !products.isEmpty();
    }
}
