package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.dto.AccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<AccountDto> getAccount();
    Mono<AccountDto> getAccountById(String id);

    //Mono<AccountDto> getAccountByName(String name);

    Mono<AccountDto> getAccountByAccountNumber(String accountNumber);

    Mono<AccountDto> saveAccount(Mono<AccountDto> accountDtoMono);

    Mono<AccountDto> updateAccount(Mono<AccountDto> accountDtoMono,String id);

    Mono<Void> deleteAccount(String id);
}
