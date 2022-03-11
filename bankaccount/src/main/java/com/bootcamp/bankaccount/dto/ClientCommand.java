package com.bootcamp.bankaccount.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCommand {
    private String name;
    private String clientNumber;
}
