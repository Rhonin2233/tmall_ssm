package com.rhonin.myssm.service;

import com.rhonin.myssm.mapper.OrderMapper;
import com.rhonin.myssm.mapper.ReviewMapper;
import com.rhonin.myssm.pojo.Order;
import com.rhonin.myssm.pojo.Review;
import com.rhonin.myssm.pojo.ReviewExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Review> listByProduct(int id) {
        ReviewExample reviewExample = new ReviewExample();
        reviewExample.createCriteria().andProduct_idEqualTo(id);
        return reviewMapper.selectByExample(reviewExample);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void add(List<Review> review, Order order) {
        for (Review r : review) {
            reviewMapper.insertSelective(r);
        }
        order.setStatus("finish");
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
