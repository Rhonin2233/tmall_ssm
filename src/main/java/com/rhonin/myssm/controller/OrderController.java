package com.rhonin.myssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhonin.myssm.pojo.*;
import com.rhonin.myssm.service.*;
import com.rhonin.myssm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserSevice userSevice;

    @RequestMapping("/listOrder")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Order> orders = orderService.list();
        page.setTotal((int) new PageInfo<>(orders).getTotal());
        for (Order o : orders) {
            o.setOrderItem(orderItemService.findByOrderId(o.getId()));
            o.setUser(userSevice.findById(o.getUser_id()));
            int price = 0;
            int num = 0;
            for (OrderItem oi : o.getOrderItem()) {
                num += oi.getNumber();
                oi.setProduct(productService.findById(oi.getProduct_id()));
                price += oi.getProduct().getPromotePrice().intValue() * oi.getNumber();
                List<ProductImage> productImages = productImageService.listSingle(oi.getProduct().getId());
                if (null != productImages && !productImages.isEmpty()) {
                    oi.getProduct().setFirstImage(productImages.get(0));
                }
            }
            o.setTotalPrice(price);
            o.setTotalNum(num);
        }
        model.addAttribute("o", orders);
        return "admin/listOrder";
    }

    @RequestMapping("/OrderDelivery")
    public String delivery(int id) throws ParseException {
        Order order = orderService.findById(id);
        order.setOrderItem(orderItemService.findByOrderId(order.getId()));
        for (OrderItem oi : order.getOrderItem()) {
            Product product = productService.findById(oi.getProduct_id());
            product.setStock(product.getStock() - oi.getNumber());
            productService.update(product);
        }
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        order.setDelivery_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s));
        order.setStatus("waitReceipt");

        orderService.delivery(order);
        return "redirect:listOrder";
    }
}
