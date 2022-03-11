package com.bootcamp.bankaccount.dto;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

    private String id;
    private double amount;
    private String clientNumber;
    //private String typeOfAccount;
    private String accountNumber;
}
