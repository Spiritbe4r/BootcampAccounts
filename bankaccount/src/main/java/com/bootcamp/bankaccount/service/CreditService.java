package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.dto.Credit;
import reactor.core.publisher.Flux;

public interface CreditService {

    //Mono<Boolean> validateDebitorCredit(String clientIdNumber);

    Flux<Credit> getCredit(String clientIdNumber);

    //Mono<CreditCard> getCreditCard(String clientIdNumber);
}
