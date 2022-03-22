package com.bootcamp.bankaccount.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
  private String pan;

  private ClientCommand client;

  private boolean debitor;
}
