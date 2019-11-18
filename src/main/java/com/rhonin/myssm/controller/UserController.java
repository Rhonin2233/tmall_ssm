package com.rhonin.myssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhonin.myssm.pojo.User;
import com.rhonin.myssm.service.UserSevice;
import com.rhonin.myssm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserSevice userSevice;

    @RequestMapping("/listUser")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> users = userSevice.list();
        page.setTotal((int) new PageInfo<>(users).getTotal());
        model.addAttribute("u", users);
        return "admin/listUser";
    }

    @RequestMapping("/deleteUser")
    public String delete(int id) {
        userSevice.delete(id);
        return "redirect:listUser";
    }
}
