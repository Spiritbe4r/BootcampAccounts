package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.bean.Account;
import com.bootcamp.bankaccount.dto.AccountDto;
import com.bootcamp.bankaccount.dto.ClientCommand;
import com.bootcamp.bankaccount.repository.AccountRepository;
import com.bootcamp.bankaccount.service.Impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

class DepositServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl service;

    private Flux<Account> fluxDto;

    private Mono<AccountDto> accountDtoMono;
    private Mono<AccountDto> accountCreated;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fluxDto = Flux.just(new Account("22575230","12345","1","123.00","USD","1",0,"1",LocalDateTime.now(), ClientCommand.builder().build(),"007"));

        accountDtoMono = Mono.just(new AccountDto("22575230","12345","1","123.00","USD","1",0,"1", ClientCommand.builder().build(),"007"));
    }

    @Test
    void getAccount() {
        Mockito.when(accountRepository.findAll()).thenReturn(fluxDto);

        Assertions.assertNotNull(service.getAccount());
    }

    @Test
    void saveAccount() {
//        Mockito.when(depositRepository.save(ArgumentMatchers.any(depositDtoMono.getClass()))).thenReturn();

        Assertions.assertNotNull(service.saveAccount(accountDtoMono,"12"));
        //Mockito.verify(depositRepository, Mockito.times(1)).save(ArgumentMatchers.any(depositDtoMono.getClass())));
    }
}