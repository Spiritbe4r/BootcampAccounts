package com.bootcamp.bankaccount.controller;

import com.bootcamp.bankaccount.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Slf4j
@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<AccountDto> getAccount(){
        //log.debug("valor:{0}","ddddd");
        LOGGER.debug("Getting clients!");
        return accountService.getClients();
    }

    @GetMapping("/{id}")
    public Mono<AccountDto> getAccount(@PathVariable String id){
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Mono<AccountDto> saveAccount(@RequestBody Mono<AccountDto> clientDtoMono){
        return accountService.saveClients(clientDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<AccountDto> updateAccount(@RequestBody Mono<AccountDto> clientDtoMono,@PathVariable String id){
        return accountService.updateAccount(clientDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable String id){
        return accountService.deleteAccount(id);
    }

}
