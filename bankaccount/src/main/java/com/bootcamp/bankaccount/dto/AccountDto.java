package com.bootcamp.bankaccount.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String typeCurrentAcc;
	private double balance;


	private String currency;

	private LocalDateTime operationDate = LocalDateTime.now();

	private ClientCommand client;

	private int movementPerMonth;

	private int maxLimitMovementPerMonth;

	private String canBeDeposit;
	private int depositLimited;


	private String clientIdNumber;



}
