package com.bootcamp.bankproduct.controller;

import com.bootcamp.bankproduct.dto.AccountDto;
import com.bootcamp.bankproduct.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<AccountDto> getAccount(){
        return accountService.getClients();
    }
}
