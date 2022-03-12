package com.cardenascode.reactive.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Document
public class Employee {

    @Id
    private String id;
    private String name;
    private double salary;

}
