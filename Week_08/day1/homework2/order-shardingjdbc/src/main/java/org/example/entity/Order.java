package org.example.entity;

import lombok.Data;

import java.util.Date;
@Data
public class OrderEntity {
    private Long id;
    private String orderNo;
    private Long userId;
    private Long itemId;
    private Double orderPrice;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
