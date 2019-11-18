package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.CategoryMapper;
import com.rhonin.myssm.pojo.Category;
import com.rhonin.myssm.pojo.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public int add(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category findById(int id) {
        return categoryMapper.selectByPrimaryKey(id);

    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}
