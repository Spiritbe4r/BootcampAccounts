package com.bootcamp.bankaccount.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
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
