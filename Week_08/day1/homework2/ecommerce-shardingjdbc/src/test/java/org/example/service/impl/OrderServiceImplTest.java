package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.entity.Order;
import org.example.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void saveOrder(){
        Order order = new Order(null,"orderNo",1370295535473528834L,1370301749800476674L,12.0,1,System.currentTimeMillis(), System.currentTimeMillis());
        boolean success = orderService.save(order);
        System.out.println(success);
    }

    @Test
    public void deleteOrder(){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        boolean success = orderService.remove(queryWrapper);
        System.out.println(success);
    }

    @Test
    public void updateOrder(){
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",1370304180772954114L);

        Order order = new Order();
        order.setOrderNo("orderNo-update");
        boolean success = orderService.update(order,updateWrapper);
        System.out.println(success);
    }

    @Test
    public void get(){
        //查询条件只有order的主键，走两个库，然后根据分片策略查t_order_2表
        Order order = orderService.getById(1370304180772954114L);
        System.out.println(order);

        //查询条件有user_id,和id，先根据user_id选择db0，再根据id选择t_order_2表
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1370304180772954114L);
        queryWrapper.eq("user_id",1370295535473528834L);
        Order order2 = orderService.getOne(queryWrapper);
        System.out.println(order2);
    }
}
