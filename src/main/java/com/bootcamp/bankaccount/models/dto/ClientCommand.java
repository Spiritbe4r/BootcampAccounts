package com.bootcamp.bankaccount.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCommand {
  private String name;
  private String code;
  private String clientIdNumber;
}
