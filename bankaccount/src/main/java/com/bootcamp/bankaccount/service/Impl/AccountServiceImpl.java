package com.bootcamp.bankaccount.service.Impl;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import com.bootcamp.bankaccount.repository.AccountRepository;
import com.bootcamp.bankaccount.service.AccountService;
import com.bootcamp.bankaccount.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor @NoArgsConstructor @Builder
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Flux<AccountDto> getAccounts() {
        return accountRepository.findAll().map(AppUtils::entityToDto);
    }

    @Override
    public Mono<AccountDto> getAccountById(String id) {
        return accountRepository.findById(id).map(AppUtils::entityToDto);
    }


    public Mono<Account> saveAccount(Account accountDtoMono) {

        return accountRepository.save(accountDtoMono);

    }

    public Mono<AccountDto> updateAccount(Mono<AccountDto> AccountDtoMono, String id) {
        return accountRepository.findById(id)
                .flatMap(p -> AccountDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(accountRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public Mono<Account> validateClientIdNumber(String customerIdentityNumber) {
        return accountRepository.findByClientIdNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Account.builder()
                        .clientIdNumber(null).build()));
    }

    @Override
    public Mono<Account> findByClientIdNumber(String clientIdNumber) {
        return accountRepository.findByClientIdNumber(clientIdNumber);
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
