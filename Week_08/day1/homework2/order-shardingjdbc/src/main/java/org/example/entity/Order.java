package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order {
    private Long id;
    private String orderNo;
    private Long userId;
    private Long itemId;
    private Double orderPrice;
    private Integer status;
    private Long createTime;
    private Long updateTime;
}
