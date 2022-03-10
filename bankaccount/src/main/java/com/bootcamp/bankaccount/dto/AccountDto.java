package com.bootcamp.bankaccount.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountDto {
	private String idAccount;
	private String balance;
	private String currency;
	private String idClient;
	private String typeSavingAcc;
	private String typeCurrentAcc;
	private String typeTermAcc;

}
