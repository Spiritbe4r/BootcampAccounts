package com.bootcamp.bankaccount.repository;

import com.bootcamp.bankaccount.bean.Account;
import com.bootcamp.bankaccount.dto.AccountDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

@Configuration
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {


    /*Mono<AccountDto> findByName(String name);*/
    //Mono<AccountDto> findByAccountNumber(String accountNumber);

    Mono<Account> findByAccountNumber(String accountNumber);
    Mono<Account> findAccountByClient_ClientNumber(String clientNumber);
}
