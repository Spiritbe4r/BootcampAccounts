package com.bootcamp.bankaccount.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	private String id;

	private String accountNumber;
	private String accountType;

	private String balance;

	private String currency;
	private String canBeDeposit;
	private int depositLimited;
	private String typeAccount;

	private ClientCommand clientCommand;

	private String clientIdNumber;

}
