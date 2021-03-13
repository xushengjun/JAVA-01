package org.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LocalCancelLog {
    private String trxNo;
    private Date createTime;
}
