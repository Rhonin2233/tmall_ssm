package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.Order;

import java.util.List;

public interface OrderService {
    String waitPayment = "waitPayment";
    String waitDelivery = "waitDelivery";
    String waitReceipt = "waitReceipt";
    String waitReview = "waitReview";
    String finish = "finish";

    List<Order> list();

    List<Order> findByUser(int id);

    List<Order> findByOrderStatus(String status);

    Boolean delete(int id);

    void delivery(Order order);

    Order findById(int id);

    void add(Order order);

    void update(Order order);
}
