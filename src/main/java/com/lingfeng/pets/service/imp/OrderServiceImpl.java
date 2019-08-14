 /**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.OrderEntity;
import com.lingfeng.pets.mapper.OrderMapper;
import com.lingfeng.pets.service.OrderService;

/**
 * @author 谷春
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
    
    /**
     * 注入订单表的接口
     */
    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public Integer insertOrder(OrderEntity orderEntity) {
        return orderMapper.insertOrder(orderEntity);
    }

    @Override
    public Integer deleteOrder(OrderEntity orderEntity) {
        return orderMapper.deleteOrder(orderEntity);
    }

    @Override
    public Integer updateOrder(OrderEntity orderEntity) {
        return orderMapper.updateOrder(orderEntity);
    }

    @Override
    public List<OrderEntity> selectAllOrder() {
        return orderMapper.selectAllOrder();
    }

    @Override
    public Integer selectCount() {
        return orderMapper.selectCount();
    }

}
