package com.rhonin.myssm.mapper;

import com.rhonin.myssm.pojo.PropertyValue;
import com.rhonin.myssm.pojo.PropertyValueExample;

import java.util.List;

public interface PropertyValueMapper {
    int deleteByPrimaryKey(Integer id);

    void deleteByPropertyId(int id);

    void deleteByProductId(int id);

    int insert(PropertyValue record);

    int insertSelective(PropertyValue record);

    List<PropertyValue> selectByExample(PropertyValueExample example);

    PropertyValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyValue record);

    int updateByPrimaryKey(PropertyValue record);
}