package com.rhonin.myssm.service;

import com.rhonin.myssm.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    List<PropertyValue> list(int id);

    int update(PropertyValue propertyValue);

    void deletebyPropertyId(int id);

    void deleteByProductId(int id);

    void add(PropertyValue propertyValue);
}
