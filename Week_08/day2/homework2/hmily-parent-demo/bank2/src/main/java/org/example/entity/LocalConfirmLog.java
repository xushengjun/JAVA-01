package org.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LocalConfirmLog {
    private String trxNo;
    private Date createTime;
}
