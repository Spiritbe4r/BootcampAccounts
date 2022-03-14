package com.bootcamp.bankaccount.handlers;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import com.bootcamp.bankaccount.models.dto.ClientCommand;
import com.bootcamp.bankaccount.service.AccountService;
import com.bootcamp.bankaccount.service.ClientService;
import com.bootcamp.bankaccount.service.CreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(AccountHandler.class);
    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private ClientService clientService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(accountService.getAccounts(), AccountDto.class);
    }

    public Mono<ServerResponse> newSavingAccount(ServerRequest request){
        Mono<Account> accountMono = request.bodyToMono(Account.class);
        return accountMono
                .flatMap(accountCreate -> clientService.getClient(accountCreate.getClientIdNumber())
                        .flatMap(customer->{
                            accountCreate.setClient(ClientCommand.builder()
                                    .name(customer.getName()).code(customer.getClientType().getCode())
                                    .clientIdNumber(customer.getClientIdNumber()).build());
                            accountCreate.setAccountType("SAVING_ACCOUNT");
                            accountCreate.setMaxLimitMovementPerMonth(accountCreate.getMaxLimitMovementPerMonth());
                            accountCreate.setMovementPerMonth(0);
                            return  accountService.saveAccount(accountCreate);
                        })
                )
                .flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                ).switchIfEmpty(ServerResponse.badRequest().build());
    }


    public Mono<ServerResponse> deleteSavingAccount(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<AccountDto> accountMono = accountService.getAccountById(id);
        return accountMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptonId={}", c.getId()))
                .flatMap(c -> accountService.deleteAccount(id).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
