package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.ProductMapper;
import com.rhonin.myssm.pojo.Product;
import com.rhonin.myssm.pojo.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> list(int id) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCategory_idEqualTo(id);
        return productMapper.selectByExample(productExample);
    }

    @Override
    public void deleteByCid(int cid) {
        productMapper.deleteByCid(cid);
    }

    @Override
    public int add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Product findById(int id) {
        return productMapper.selectByPrimaryKey(id);

    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andNameLike("%" + keyword + "%");
        return productMapper.selectByExample(productExample);
    }
}
