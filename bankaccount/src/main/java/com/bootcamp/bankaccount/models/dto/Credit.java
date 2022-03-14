package com.bootcamp.bankaccount.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private String contractNumber;

    private ClientCommand client;

    private boolean debitor ;
}
