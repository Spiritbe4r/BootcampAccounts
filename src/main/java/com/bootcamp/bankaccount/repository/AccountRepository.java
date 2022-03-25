package com.bootcamp.bankaccount.repository;

import com.bootcamp.bankaccount.models.bean.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

@Configuration
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {


  /*Mono<AccountDto> findByName(String name);*/
  //Mono<AccountDto> findByAccountNumber(String accountNumber);

  Mono<Account> findByAccountNumber(String accountNumber);

  Mono<Account> findByClientIdNumber(String clientIdNumber);
}
