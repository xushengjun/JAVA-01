package org.example.entity;

import lombok.Data;

import java.util.Date;
@Data
public class UserEntity {
    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private String idCard;
    private Date createTime;
    private Date updateTime;

}
