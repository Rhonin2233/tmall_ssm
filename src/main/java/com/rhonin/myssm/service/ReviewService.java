package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.Order;
import com.rhonin.myssm.pojo.Review;

import java.util.List;

public interface ReviewService {
    List<Review> listByProduct(int id);

    void add(List<Review> review, Order order);
}
