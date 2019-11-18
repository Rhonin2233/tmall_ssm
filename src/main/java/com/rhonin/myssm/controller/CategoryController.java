package com.rhonin.myssm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.service.CategoryService;
import com.rhonin.myssm.service.ProductService;
import com.rhonin.myssm.service.PropertyService;
import com.rhonin.myssm.util.Page;
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
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("/listCategory")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> categories = categoryService.list();
        page.setTotal((int) new PageInfo<>(categories).getTotal());
        model.addAttribute("categories", categories);
        return "admin/listCategory";
    }

    @RequestMapping("/addCategory")
    public String add(HttpServletRequest request, Category category, MultipartFile image) throws IOException {
        categoryService.add(category);
        //获取返回的id
        String name = category.getId() + ".jpg";
        File newFile = new File(request.getServletContext().getRealPath("img/category"), name);
        newFile.getParentFile().mkdirs();
        image.transferTo(newFile);
        return "redirect:listCategory";
    }

    @RequestMapping("/deleteCategory")
    public String delete(HttpServletRequest request, int id) {
        propertyService.deleteByCid(id);
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"), id + ".jpg");
        imageFolder.delete();
        return "redirect:listCategory";
    }

    @RequestMapping("/editCategory")
    public String edit(Model model, int id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @RequestMapping("/updateCategory")
    public String update(Category category, HttpServletRequest request, MultipartFile image) throws IOException {
        categoryService.update(category);
        System.out.println(image);
        if (null != image && !image.isEmpty()) {
            File imageFloder = new File(request.getServletContext().getRealPath("img/category"), category.getId() + ".jpg");
            image.transferTo(imageFloder);
        }
        return "redirect:listCategory";
    }
}
