package com.bootcamp.bankproduct.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("account")
@Data
public class Account {
	@Id
	private String idAccount;
	private String balance;
	private String currency;
	private String idClient;
	private String typeSavingAcc;
	private String typeCurrentAcc;
	private String typeTermAcc;

}
