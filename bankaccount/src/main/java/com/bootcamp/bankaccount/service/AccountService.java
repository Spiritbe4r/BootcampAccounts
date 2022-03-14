package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<AccountDto> getAccounts();
    Mono<AccountDto> getAccountById(String id);


    Mono<Account> saveAccount(Account accountDtoMono);

    Mono<AccountDto> updateAccount(Mono<AccountDto> accountDtoMono,String id);

    Mono<Void> deleteAccount(String id);

    Mono<Account> validateClientIdNumber(String clientIdNumber);

    Mono<Account> findByClientIdNumber(String clientIdNumber);

    Mono<Account> findByAccountNumber(String accountNumber);
}
