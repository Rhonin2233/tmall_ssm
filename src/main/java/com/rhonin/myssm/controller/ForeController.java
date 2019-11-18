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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserSevice userSevice;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        List<Category> categories = categoryService.list();
        Page page = new Page();
        for (Category c : categories) {
            List<Object> list = new ArrayList<>();
            for (int i = page.getStart(); i <= page.getTotal(); i += page.getCount()) {
                PageHelper.offsetPage(i, page.getCount());
                List<Product> products = productService.list(c.getId());
                for (Product p : products) {
                    List<ProductImage> productImages = productImageService.listSingle(p.getId());
                    if (null != productImages && !productImages.isEmpty()) {
                        p.setFirstImage(productImages.get(0));
                    }
                }
                page.setTotal((int) new PageInfo<>(products).getTotal());
                list.add(products);
            }
            c.setProductsByRow(list);
        }
        int count = categories.size();
        model.addAttribute("count", count);
        session.setAttribute("cs", categories);
        return "fore/home";
    }

    @RequestMapping("/foreregister")
    public String register(Model model, User user) {
        if (userSevice.checkByName(user.getName())) {
            model.addAttribute("msg", "用户名已被注册");
            return "fore/register";
        } else {
            userSevice.add(user);
            return "redirect:registerSuccessPage";
        }
    }

    @RequestMapping("/forelogin")
    public String login(Model model, User user, HttpSession session) {
        User user1 = userSevice.login(user);
        if (null != user1) {
            session.setAttribute("user", user1);
            return "redirect:home";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "fore/login";
        }
    }

    @RequestMapping("/checklogout")
    public String logout(HttpSession session) {
        session.removeAttribute("cartTotalItemNumber");
        session.removeAttribute("user");
        return "redirect:home";
    }

    @RequestMapping("/foreproduct")
    public String productPage(Model model, OrderItem orderItem, HttpServletRequest request) {
        int saleCount = 0;
        Product product = productService.findById(orderItem.getProduct_id());
        product.setCategory(categoryService.findById(product.getCategory_id()));
        List<PropertyValue> propertyValues = propertyValueService.list(product.getId());
        for (PropertyValue pv : propertyValues) {
            pv.setProperty(propertyService.findById(pv.getProperty_id()));
        }
        List<Review> reviews = reviewService.listByProduct(product.getId());
        if (null != reviews && !reviews.isEmpty()) {
            for (Review r : reviews) {
                User user = userSevice.findById(r.getUser_id());
                String name = user.getName();
                String s1 = name.substring(1, name.length() - 1);
                s1 = s1.replaceAll("/w/W", "*");
                s1 = name.charAt(0) + s1 + name.charAt(name.length() - 1);
                user.setAnonymous(s1);
                r.setUser(user);
            }
        }
        List<OrderItem> orderItems = orderItemService.findByProductId(product.getId());
        if (null != orderItems && !orderItems.isEmpty()) {
            for (OrderItem oi : orderItems) {
                saleCount += oi.getNumber();
            }
        }
        List<ProductImage> productImages = productImageService.listSingle(product.getId());
        if (null != productImages && !productImages.isEmpty()) {
            product.setProductSingleImages(productImages);
            product.setFirstImage(productImages.get(0));
        }
        List<ProductImage> productDetailImages = productImageService.listDetail(product.getId());
        product.setProductDetailImages(productDetailImages);
        product.setReviewCount(reviews.size());
        product.setSaleCount(saleCount + 1000);
        model.addAttribute("pv", propertyValues);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", product);
        return "fore/product";
    }

    @RequestMapping("/forecheckLogin")
    @ResponseBody
    public Boolean checkLogin(HttpSession session) {
        return null != session.getAttribute("user");
    }

    @RequestMapping("/foreloginAjax")
    @ResponseBody
    public Boolean loginAjax(User user, HttpSession session) {
        User user1 = userSevice.login(user);
        if (null != user1) {
            session.setAttribute("user", user1);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/forecategory")
    public String categoryPage(Model model, Product product, HttpServletRequest request) {
        Category category = categoryService.findById(product.getCategory_id());
        List<Product> products = productService.list(category.getId());
        for (Product p : products) {
            int saleCount = 0;
            List<Review> reviews = reviewService.listByProduct(p.getId());
            List<OrderItem> orderItems = orderItemService.findByProductId(p.getId());
            if (null != orderItems && !orderItems.isEmpty()) {
                for (OrderItem oi : orderItems) {
                    saleCount += oi.getNumber();
                }
            }
            List<ProductImage> productImages = productImageService.listSingle(p.getId());
            if (null != productImages && !productImages.isEmpty()) {
                p.setFirstImage(productImages.get(0));
            }
            p.setReviewCount(reviews.size());
            p.setSaleCount(saleCount + 1000);
        }
        String sort = request.getParameter("sort");
        if (null != sort) {
            switch (sort) {
                case "all":
                    break;
                case "review":
                    Collections.sort(products, Comparator.comparing(Product::getReviewCount));
                    break;
                case "date":
                    Collections.sort(products, Comparator.comparing(Product::getId));
                    break;
                case "saleCount":
                    Collections.sort(products, Comparator.comparing(Product::getSaleCount));
                    break;
                case "price":
                    Collections.sort(products, Comparator.comparing(Product::getPromotePrice));
                    break;
            }
            model.addAttribute("sort", sort);
        }
        category.setProducts(products);
        model.addAttribute("c", category);
        return "fore/category";
    }

    @RequestMapping("/foresearch")
    public String search(Model model, String keyword) {
        List<Product> products = productService.search(keyword);
        for (Product p : products) {
            int saleCount = 0;
            List<Review> reviews = reviewService.listByProduct(p.getId());
            List<OrderItem> orderItems = orderItemService.findByProductId(p.getId());
            if (null != orderItems && !orderItems.isEmpty()) {
                for (OrderItem oi : orderItems) {
                    saleCount += oi.getNumber();
                }
            }
            List<ProductImage> productImages = productImageService.listSingle(p.getId());
            if (null != productImages && !productImages.isEmpty()) {
                p.setFirstImage(productImages.get(0));
            }
            p.setReviewCount(reviews.size());
            p.setSaleCount(saleCount + 1000);
        }
        model.addAttribute("ps", products);
        return "fore/searchResult";
    }

    @RequestMapping("/checkbuyone")
    public String buy(OrderItem orderItem, HttpSession session, Model model) {
        int total = 0;
        User user = (User) session.getAttribute("user");
        Product product = productService.findById(orderItem.getProduct_id());
        List<ProductImage> productImages = productImageService.listSingle(product.getId());
        if (null != productImages && !productImages.isEmpty()) {
            product.setFirstImage(productImages.get(0));
        }
        total = orderItem.getNumber() * product.getPromotePrice().intValue();
        orderItem.setUser_id(user.getId());
        orderItem.setProduct(product);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        model.addAttribute("total", total);
        model.addAttribute("ois", orderItems);
        return "fore/buy";
    }

    @RequestMapping("/checkaddCart")
    @ResponseBody
    public Boolean addCart(OrderItem orderItem, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Product product = productService.findById(orderItem.getProduct_id());
        orderItem.setUser_id(user.getId());
        return orderItemService.add(orderItem) == 1;
    }

    @RequestMapping("/checkcart")
    public String cart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.userCart(user.getId());
        for (OrderItem oi : orderItems) {
            Product product = productService.findById(oi.getProduct_id());
            List<ProductImage> productImages = productImageService.listSingle(product.getId());
            if (null != productImages && !productImages.isEmpty()) {
                product.setFirstImage(productImages.get(0));
            }
            oi.setProduct(product);
        }
        model.addAttribute("ois", orderItems);
        return "fore/cart";
    }

    @RequestMapping("/checkdeleteOrderItem")
    @ResponseBody
    public Boolean deleteOritem(int oiid) {
        return orderItemService.delete(oiid) == 1;
    }

    @RequestMapping("/checkbuy")
    public String createOrder(Model model, int[] oiid) {
        int total = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (int id : oiid) {
            OrderItem orderItem = orderItemService.findById(id);
            Product product = productService.findById(orderItem.getProduct_id());
            List<ProductImage> productImages = productImageService.listSingle(product.getId());
            if (null != productImages && !productImages.isEmpty()) {
                product.setFirstImage(productImages.get(0));
            }
            total += orderItem.getNumber() * product.getPromotePrice().intValue();
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }
        model.addAttribute("total", total);
        model.addAttribute("ois", orderItems);
        return "fore/buy";
    }

    @RequestMapping("/checkcreateOrder")
    public String comfirmOrder(Order order, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        order.setOrder_code(orderCode);
        order.setOrderItem(orderItemService.userCart(user.getId()));
        order.setCreate_date(new Date());
        order.setUser_id(user.getId());
        orderService.add(order);
        model.addAttribute("o", order);
        return "fore/alipay";
    }

    @RequestMapping("/checkpayed")
    public String comfirmPay(Order order, Model model) {
        order.setStatus("waitDelivery");
        order.setPay_date(new Date());
        orderService.update(order);
        model.addAttribute("total", order.getTotalPrice());
        model.addAttribute("o", orderService.findById(order.getId()));
        return "fore/payed";
    }

    @RequestMapping("/checkbought")
    public String myOrder(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findByUser(user.getId());
        for (Order o : orders) {
            o.setOrderItem(orderItemService.findByOrderId(o.getId()));
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
        model.addAttribute("os", orders);
        return "fore/bought";
    }

    @RequestMapping("/checkdeleteOrder")
    @ResponseBody
    public Boolean deleteOrder(int id) {
        return orderService.delete(id);
    }

    @RequestMapping("/checkconfirmPay")
    public String Receipt(int id, Model model) {
        Order o = orderService.findById(id);
        o.setOrderItem(orderItemService.findByOrderId(o.getId()));
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
        model.addAttribute("o", o);
        return "fore/confirmPay";
    }

    @RequestMapping("/checkorderConfirmed")
    public String comfirmReceipt(int id) {
        Order order = orderService.findById(id);
        order.setConfirm_date(new Date());
        order.setStatus("waitReview");
        orderService.update(order);
        return "fore/orderConfirmed";
    }

    @RequestMapping("/checkreview")
    public String review(int id, Model model, HttpSession session) {
        List<OrderItem> orderItems = orderItemService.findByOrderId(id);
        List<Product> products = new ArrayList<>();
        for (OrderItem oi : orderItems) {
            Product product = productService.findById(oi.getProduct_id());
            List<ProductImage> productImages = productImageService.listSingle(product.getId());
            if (null != productImages && !productImages.isEmpty()) {
                product.setFirstImage(productImages.get(0));
            }
            products.add(product);
        }
        model.addAttribute("ps", products);
        session.setAttribute("oid", id);
        return "fore/review";
    }

    @RequestMapping("/checkdoreview")
    public String comfirmReview(int[] product_id, String[] content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < product_id.length; i++) {
            Review review = new Review();
            review.setContent(content[i]);
            review.setProduct_id(product_id[i]);
            review.setUser_id(user.getId());
            review.setCreateDate(new Date());
            reviews.add(review);
        }
        int oid = (int) session.getAttribute("oid");
        Order order = orderService.findById(oid);
        reviewService.add(reviews, order);
        session.removeAttribute("oid");
        return "fore/reviewSuccess";
    }

    @RequestMapping("/checkuser")
    public String editUser(User user, HttpSession session) {
        User user1 = (User) session.getAttribute("user");
        user.setId(user1.getId());
        userSevice.update(user);
        return "fore/editSuccess";
    }
}
