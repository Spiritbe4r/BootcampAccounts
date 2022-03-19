package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.dto.Credit;
import com.bootcamp.bankaccount.models.dto.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Mono<Boolean> validateDebitorCredit(String clientIdNumber);

    Flux<Credit> getCredit(String clientIdNumber);

    Mono<CreditCard> getCreditCard(String clientIdNumber);
}
