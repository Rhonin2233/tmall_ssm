package com.rhonin.myssm.mapper;

import com.rhonin.myssm.pojo.OrderItem;
import com.rhonin.myssm.pojo.OrderItemExample;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    void deleteByOrder(int id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}