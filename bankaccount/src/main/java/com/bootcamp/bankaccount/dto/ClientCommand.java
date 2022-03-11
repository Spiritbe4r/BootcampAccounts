package com.bootcamp.bankaccount.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCommand {
    private String name;
    private String code;
    private String clientNumber;
}
