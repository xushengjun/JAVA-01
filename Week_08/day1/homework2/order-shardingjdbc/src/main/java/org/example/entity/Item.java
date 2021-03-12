package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@TableName("t_item")
public class Item {
    private Long id;
    @TableField("item_hs_code")
    private String itemHSCode;
    private String itemName;
    private Integer itemType;
    private Double itemWeight;
    private Double itemPrice;
    private Integer status;
    private Long createTime;
    private Long updateTime;

}
