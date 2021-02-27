package org.example.service;

import org.example.entity.ItemEntity;
import org.example.entity.OrderEntity;
import org.example.mapper.ItemMapper;
import org.example.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemMapper itemMapper;

    public void add(Long userId, Long itemId) {
        ItemEntity item = itemMapper.findById(itemId);
        Double orderPrice = item.getItemPrice();
        //TODO 插入订单
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo("ORDER_SKDHFWIE");
        orderEntity.setStatus(1);
        orderEntity.setStatus(1);
        orderEntity.setUserId(userId);
        orderEntity.setItemId(itemId);
        orderEntity.setOrderPrice(orderPrice);
        orderMapper.insertOne(orderEntity);
    }

    public OrderEntity findById(Long orderId) {
        return orderMapper.findById(orderId);
    }
}
