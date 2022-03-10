package com.bootcamp.bankproduct.controller;

import com.bootcamp.bankproduct.dto.AccountDto;
import com.bootcamp.bankproduct.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<AccountDto> getAccount(){
        LOGGER.debug("Getting clients!");
        return accountService.getClients();
    }
}
