package com.bootcamp.bankaccount.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	private String id;
	private String balance;
	private String currency;
	private String idClient;
	private String accountNumber;
	private String typeSavingAcc;
	private String typeCurrentAcc;
	private String typeTermAcc;

}
