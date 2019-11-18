package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    int add(Category category);

    void delete(int id);

    Category findById(int id);

    void update(Category category);
}
