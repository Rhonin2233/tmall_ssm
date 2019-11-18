package com.rhonin.myssm.mapper;

import com.rhonin.myssm.pojo.ProductImage;
import com.rhonin.myssm.pojo.ProductImageExample;

import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Integer id);

    void deleteByPid(int id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}