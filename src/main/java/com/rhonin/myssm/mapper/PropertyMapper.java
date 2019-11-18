package com.rhonin.myssm.mapper;

import com.rhonin.myssm.pojo.Property;
import com.rhonin.myssm.pojo.PropertyExample;

import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByCid(int id);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}