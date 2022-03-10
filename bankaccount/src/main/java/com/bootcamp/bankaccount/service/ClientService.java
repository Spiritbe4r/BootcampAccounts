package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.dto.AccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<AccountDto>getClients();
    Mono<AccountDto> getClientById(String id);

    Mono<AccountDto> getClientByName(String name);

    Mono<AccountDto> getClientByClientNumber(String clientNumber);

    Mono<AccountDto> saveClients(Mono<AccountDto> AccountDtoMono);

    Mono<AccountDto> updateClients(Mono<AccountDto> AccountDtoMono,String id);

    Mono<Void> deleteClient(String id);
}
