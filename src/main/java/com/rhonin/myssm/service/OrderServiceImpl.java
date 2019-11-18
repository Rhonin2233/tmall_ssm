package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.OrderItemMapper;
import com.rhonin.myssm.mapper.OrderMapper;
import com.rhonin.myssm.pojo.Order;
import com.rhonin.myssm.pojo.OrderExample;
import com.rhonin.myssm.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<Order> list() {
        OrderExample orderExample = new OrderExample();
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> findByUser(int id) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUser_idEqualTo(id);
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public List<Order> findByOrderStatus(String status) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andStatusEqualTo(status);
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public Boolean delete(int id) {
        orderItemMapper.deleteByOrder(id);
        return orderMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public void delivery(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order findById(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void add(Order order) {
        order.setStatus(waitPayment);
        orderMapper.insertSelective(order);
        for (OrderItem oi : order.getOrderItem()) {
            oi.setOrder_id(order.getId());
            orderItemMapper.updateByPrimaryKey(oi);
        }

    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

}
