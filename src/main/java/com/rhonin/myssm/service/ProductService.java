package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(int id);

    void deleteByCid(int cid);

    int add(Product product);

    void delete(int id);

    Product findById(int id);

    void update(Product product);

    List<Product> search(String keyword);
}
