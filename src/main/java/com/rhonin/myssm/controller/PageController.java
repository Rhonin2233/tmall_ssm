package com.rhonin.myssm.controller;

import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class PageController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "fore/register";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "fore/login";
    }

    @RequestMapping("/checkeditUser")
    public String userPage() {
        return "fore/user";
    }

}
