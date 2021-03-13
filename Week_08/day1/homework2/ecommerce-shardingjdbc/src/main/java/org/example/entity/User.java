package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private String idCard;
    private Long createTime;
    private Long updateTime;

}
