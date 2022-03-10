package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.dto.AccountDto;
import com.bootcamp.bankaccount.repository.AccountRepository;
import com.bootcamp.bankaccount.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {





    @Autowired
    private AccountRepository accountRepository;

    public Flux<AccountDto> getAccount() {
        return accountRepository.findAll().map(AppUtils::entityToDto);
    }

    @Override
    public Mono<AccountDto> getAccountById(String id) {
        return accountRepository.findById(id).map(AppUtils::entityToDto);
    }
/*
    @Override
    public Mono<AccountDto> getAccountByName(String name) {
        return accountRepository.findByName(name);
    }*/

    @Override
    public Mono<AccountDto> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.just(AccountDto.builder()
                        .accountNumber(null).build()));
    }


    public Mono<AccountDto> saveAccount(Mono<AccountDto> AccountDtoMono) {
        return AccountDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(accountRepository::insert)
                .map(AppUtils::entityToDto);
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
}
