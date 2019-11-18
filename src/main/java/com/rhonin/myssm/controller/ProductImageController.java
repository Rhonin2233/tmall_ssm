package com.rhonin.myssm.controller;

import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.pojo.Product;
import com.rhonin.myssm.pojo.ProductImage;
import com.rhonin.myssm.service.CategoryService;
import com.rhonin.myssm.service.ProductImageService;
import com.rhonin.myssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/listProductImage")
    public String list(Model model, int product_id) {
        Product product = productService.findById(product_id);
        Category category = categoryService.findById(product.getCategory_id());
        List<ProductImage> productSingleImage = productImageService.listSingle(product_id);
        List<ProductImage> productDetailImage = productImageService.listDetail(product_id);
        model.addAttribute("p", product);
        model.addAttribute("c", category);
        model.addAttribute("ps", productSingleImage);
        model.addAttribute("pd", productDetailImage);
        return "admin/listProductImage";
    }

    @RequestMapping("/addProductImage")
    public String add(HttpServletRequest request, ProductImage productImage, MultipartFile image) throws IOException {
        productImageService.add(productImage);
        //获取返回的id
        String name = productImage.getId() + ".jpg";
        String filePath = null;
        if ("single".equals(productImage.getType())) {
            filePath = request.getServletContext().getRealPath("img/productSingle");
        }
        if ("detail".equals(productImage.getType())) {
            filePath = request.getServletContext().getRealPath("img/productDetail");
        }
        System.out.println(productImage.getType());
        System.out.println(filePath);
        File newFile = new File(filePath, name);
        newFile.getParentFile().mkdirs();
        image.transferTo(newFile);
        return "redirect:listProductImage?product_id=" + productImage.getProduct_id();
    }

    @RequestMapping("/deleteProductImage")
    public String delete(HttpServletRequest request, ProductImage productImage) {
        String filePath = null;
        if ("single".equals(productImage.getType())) {
            filePath = request.getServletContext().getRealPath("img/productSingle");
        }
        if ("detail".equals(productImage.getType())) {
            filePath = request.getServletContext().getRealPath("img/productDetail");
        }
        productImageService.delete(productImage.getId());
        File imageFolder = new File(filePath, productImage.getId() + ".jpg");
        imageFolder.delete();
        System.out.println(productImage.getProduct_id());
        return "redirect:listProductImage?product_id=" + productImage.getProduct_id();
    }
}
