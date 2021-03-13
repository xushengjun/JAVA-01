package org.example.entity;

import lombok.Data;

@Data
public class AccountInfo  {
	private Long id;
	private String accountName;
	private String accountNo;
	private String accountPassword;
	private Double accountBalance;

}