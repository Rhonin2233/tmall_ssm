package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> listSingle(int id);

    List<ProductImage> listDetail(int id);

    void delete(int id);

    void deleteByPid(int id);

    int add(ProductImage productImage);

}
