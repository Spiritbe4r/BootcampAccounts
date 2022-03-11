package com.bootcamp.bankproduct.bean;

import com.bootcamp.bankproduct.dto.ClientDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("account")
@Data
public class Account {
	@Id
	private String id;
	private String amount;
	private String currency;

	private String typeSavingAcc;
	private String typeCurrentAcc;

	private ClientDto clientDto;

}
