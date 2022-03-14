package com.bootcamp.bankaccount.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCommand {
    private String name;
    private String code;
    private String clientIdNumber;
}
