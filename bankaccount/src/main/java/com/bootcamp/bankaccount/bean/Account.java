package com.bootcamp.bankaccount.bean;

import com.bootcamp.bankaccount.dto.ClientCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Account {
	@Id
	private String id;

	private String accountNumber;
	private String typeCurrentAcc;
	private String balance;

	private String currency;
	private String canBeDeposit;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime operationDate = LocalDateTime.now();

	private ClientCommand client;

	private String clientIdNumber;

}
