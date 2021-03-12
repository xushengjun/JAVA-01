package org.example.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ItemEntity {
    private Long id;
    private String itemHSCode;
    private String itemName;
    private Integer itemType;
    private Double itemWeight;
    private Double itemPrice;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
