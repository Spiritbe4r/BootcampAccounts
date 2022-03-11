package com.bootcamp.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerQuery {
    private String name;
    private String customerIdentityNumber;
    private String customerIdentityType;
    private CustomerTypeRequest customerType;
}