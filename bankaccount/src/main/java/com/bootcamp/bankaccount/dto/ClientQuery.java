package com.bootcamp.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientQuery {
    private String name;
    private String clientNumber;
    //private String clientIdentityType;
    private ClientTypeRequest clientType;
}