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

	private String accountNumber;
	private String accountType;

	private String currency;
	private String canBeDeposit;

	private ClientCommand clientCommand;


}
