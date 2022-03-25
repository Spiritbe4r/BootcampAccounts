package com.bootcamp.bankaccount.models.bean;


import com.bootcamp.bankaccount.models.dto.ClientCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(value = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

  @Id
  private String id;

  private String accountNumber;

  private String typeOfAccount;

  private double amount;

  private String currency;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime operationDate = LocalDateTime.now();

  private ClientCommand client;

  private int movementPerMonth;

  private int maxLimitMovementPerMonth;

  private String clientIdNumber;

}
