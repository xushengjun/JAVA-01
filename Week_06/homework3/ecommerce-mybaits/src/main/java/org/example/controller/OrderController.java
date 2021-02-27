package org.example.controller;

import org.example.entity.OrderEntity;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "order",produces = "application/json; charset=utf-8")//解决中文乱码
public class OrderController {
    @Autowired
    private OrderService  orderService;
    @GetMapping("/add")
    public void addOne(){
        Long userId = 1L;
        Long itemId = 2L;
        orderService.add(userId,itemId);
    }
    @GetMapping("/addlist")
    public void addBatch(){

    }
    @GetMapping("/get")
    public OrderEntity get(@RequestParam(name = "orderid")Long orderId){
        return orderService.findById(orderId);
    }
}
