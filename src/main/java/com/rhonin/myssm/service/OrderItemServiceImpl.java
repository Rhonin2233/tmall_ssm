package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.OrderItemMapper;
import com.rhonin.myssm.pojo.OrderItem;
import com.rhonin.myssm.pojo.OrderItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> findByOrderId(int id) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrder_idEqualTo(id);
        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public List<OrderItem> findByProductId(int id) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andProduct_idEqualTo(id);
        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public int add(OrderItem orderItem) {
        return orderItemMapper.insertSelective(orderItem);
    }

    @Override
    public List<OrderItem> userCart(int id) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andUser_idEqualTo(id).andOrder_idIsNull();
        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public int delete(int id) {
        return orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderItem findById(int id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }
}
