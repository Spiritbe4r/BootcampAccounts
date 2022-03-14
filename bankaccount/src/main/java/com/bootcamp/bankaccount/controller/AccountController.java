package com.bootcamp.bankaccount.controller;

import com.bootcamp.bankaccount.models.dto.AccountDto;
import com.bootcamp.bankaccount.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Slf4j
@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private AccountService accountService;


    @GetMapping
    public Flux<AccountDto> getAccount(){
        //log.debug("valor:{0}","ddddd");
        LOGGER.debug("Getting Accounts!");

        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Mono<AccountDto> getAccount(@PathVariable String id){
        LOGGER.debug("Getting a accounts!");
        return accountService.getAccountById(id);
    }


    /*@PostMapping()
    public Mono<AccountDto> saveAccount(@RequestBody Mono<AccountDto> accountDtoMono){
        LOGGER.debug("Saving clients!");
        return accountService.saveAccount(accountDtoMono);
    }*/

    @PutMapping("/{id}")
    public Mono<AccountDto> updateAccount(@RequestBody Mono<AccountDto> accountDtoMono,@PathVariable String id){
        LOGGER.debug("Updating accounts!");
        return accountService.updateAccount(accountDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable String id){
        LOGGER.debug("Deleting accounts!");
        return accountService.deleteAccount(id);
    }

}
