package com.bootcamp.bankaccount.models.bean;

import com.bootcamp.bankaccount.models.dto.ClientCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {
	@Id
	private String id;

	private String accountNumber;
	private String typeCurrentAcc;
	private double  balance;

	private String currency;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime operationDate = LocalDateTime.now();

	private ClientCommand client;

	private int movementPerMonth;

	private int maxLimitMovementPerMonth;

	private String clientIdNumber;

}
