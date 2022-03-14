package com.bootcamp.bankaccount.controller;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import com.bootcamp.bankaccount.models.dto.ClientCommand;
import com.bootcamp.bankaccount.repository.AccountRepository;
import com.bootcamp.bankaccount.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

class DepositControllerTest {
    @Mock
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountController controller;

    private AccountDto dto;
    Flux<AccountDto> fluxDto;
    Flux<Account> fluxDo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new AccountDto("22575230","12345",123.00,"USD","1","1",LocalDateTime.now(), ClientCommand.builder().build(),0,3,"007");
        fluxDto = Flux.just(dto);
        fluxDo = Flux.just(new Account("22575230","12345",123.00,"USD","1","1",LocalDateTime.now(), ClientCommand.builder().build(),0,3,"007"));    }

    @Test
    void getAccount() {
        Mockito.when(accountService.getAccounts()).thenReturn(fluxDto);
        Assertions.assertNotNull(controller.getAccount());
    }
}