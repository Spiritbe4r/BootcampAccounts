package com.bootcamp.bankaccount.dto;

import lombok.Data;

@Data
public class Credit {

    private String contractNumber;

    private ClientCommand client;

    private boolean debtor;
}
