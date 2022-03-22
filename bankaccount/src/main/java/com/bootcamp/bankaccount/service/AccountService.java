package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

  Flux<AccountDto> getAccounts();

  Mono<Account> getAccountById(String id);

  Mono<Account> update(Account obj);

  Mono<Account> saveAccount(Account accountDtoMono);

  Mono<AccountDto> updateAccount(Mono<AccountDto> accountDtoMono, String id);

  Mono<Void> deleteAccount(String id);

  /**
   * Validate client identity number mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Mono<Account> validateClientIdNumber(String clientIdNumber);

  /**
   * Find by client identity number mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Mono<Account> findByClientIdNumber(String clientIdNumber);

  /**
   * Find by account number mono.
   *
   * @param accountNumber the account number
   * @return the mono
   */
  Mono<Account> findByAccountNumber(String accountNumber);
}
