package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import com.bootcamp.bankaccount.models.dto.ClientCommand;
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
    private Account account;
    private AccountDto accountDto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Account account = new Account("22575230","12345",123.00,"USD","1","1",LocalDateTime.now(), ClientCommand.builder().build(),0,3,"007");
        fluxDto = Flux.just();
        accountDto = new AccountDto("22575230","12345",123.00,"USD","1","1",LocalDateTime.now(), ClientCommand.builder().build(),0,3,"007");
        accountDtoMono = Mono.just(accountDto);
    }

    @Test
    void getAccount() {
        Mockito.when(accountRepository.findAll()).thenReturn(fluxDto);

        Assertions.assertNotNull(service.getAccounts());
    }

    @Test
    void saveAccount() {
//        Mockito.when(depositRepository.save(ArgumentMatchers.any(depositDtoMono.getClass()))).thenReturn();

        Assertions.assertNotNull(service.saveAccount(account));
        //Mockito.verify(depositRepository, Mockito.times(1)).save(ArgumentMatchers.any(depositDtoMono.getClass())));
    }
}