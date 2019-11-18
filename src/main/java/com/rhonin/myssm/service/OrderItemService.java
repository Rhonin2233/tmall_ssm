package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findByOrderId(int id);

    List<OrderItem> findByProductId(int id);

    int add(OrderItem orderItem);

    List<OrderItem> userCart(int id);

    int delete(int id);

    OrderItem findById(int id);
}
