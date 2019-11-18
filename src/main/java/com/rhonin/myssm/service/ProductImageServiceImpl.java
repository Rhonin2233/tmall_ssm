package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.ProductImageMapper;
import com.rhonin.myssm.pojo.ProductImage;
import com.rhonin.myssm.pojo.ProductImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> listSingle(int id) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria().andProduct_idEqualTo(id).andTypeEqualTo("single");
        return productImageMapper.selectByExample(productImageExample);
    }

    @Override
    public List<ProductImage> listDetail(int id) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria().andProduct_idEqualTo(id).andTypeEqualTo("detail");
        return productImageMapper.selectByExample(productImageExample);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByPid(int id) {
        productImageMapper.deleteByPid(id);
    }

    @Override
    public int add(ProductImage productImage) {
        return productImageMapper.insert(productImage);
    }
}
