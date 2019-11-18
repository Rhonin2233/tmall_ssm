package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.Property;

import java.util.List;

public interface PropertyService {
    List<Property> list(int id);

    int add(Property property);

    void delete(int id);

    void deleteByCid(int id);

    Property findById(int id);

    void update(Property property);
}
