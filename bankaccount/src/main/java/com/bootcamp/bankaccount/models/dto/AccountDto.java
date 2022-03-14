package com.bootcamp.bankaccount.models.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	private String id;

	private String accountNumber;

	private double balance;

	private String currency;

	private String accountType;

	private String canBeDeposit;

	private LocalDateTime operationDate = LocalDateTime.now();

	private ClientCommand client;

	private int movementPerMonth;

	private int maxLimitMovementPerMonth;

	private String clientIdNumber;



}
