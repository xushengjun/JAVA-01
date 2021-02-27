package org.example.mapper;


import org.example.entity.OrderEntity;

import java.util.List;

public interface OrderMapper {
    int insertOne(OrderEntity order);
    int insertBatch(List<OrderEntity> orderList);

    OrderEntity findById(Long orderId);
}
