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

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountHandler.class);
  @Autowired
  private AccountService accountService;

  @Autowired
  private CreditService creditService;

  @Autowired
  private ClientService clientService;

  /**
   * Find all mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
          .body(accountService.getAccounts(), AccountDto.class);
  }

  /**
   * Find account by accountNumber.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> findByAccountNumber(ServerRequest request) {
    String accountNumber = request.pathVariable("accountNumber");
    LOGGER.info("The AccountNumber is " + accountNumber);
    return accountService.findByAccountNumber(accountNumber).flatMap(c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)))
          .switchIfEmpty(ServerResponse.notFound().build());
  }

  /**
   * save account mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> newSavingAccount(ServerRequest request) {
    Mono<Account> accountMono = request.bodyToMono(Account.class);
    return accountMono
      .flatMap(accountCreate -> clientService.getClient(accountCreate.getClientIdNumber())
                .flatMap(customer -> {
                  accountCreate.setClient(ClientCommand.builder()
                        .name(customer.getName()).code(customer.getClientType().getCode())
                        .clientIdNumber(customer.getClientIdNumber()).build());
                  accountCreate.setTypeOfAccount("SAVING_ACCOUNT");
                  accountCreate.setMaxLimitMovementPerMonth(accountCreate.getMaxLimitMovementPerMonth());
                  accountCreate.setMovementPerMonth(0);
                  return creditService.validateDebtorCredit(accountCreate.getClientIdNumber())
                        .flatMap(debitor -> {
                          if (debitor == true) {
                            return Mono.empty();
                          } else {
                            return accountService.validateClientIdNumber(accountCreate.getClientIdNumber());
                          }
                        })
                        .flatMap(accountFound -> {
                          if (accountFound.getClientIdNumber() != null) {
                            LOGGER.info("La cuenta encontrada es: " + accountFound.getClientIdNumber());
                            return Mono.empty();
                          } else {
                            LOGGER.info("No se encontró la cuenta ");
                            return accountService.saveAccount(accountCreate);
                          }
                        });
                })
          )
          .flatMap(c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
          ).switchIfEmpty(ServerResponse.badRequest().build());
  }

  public Mono<Account> validateDebitor(Account account) {
    return creditService.validateDebtorCredit(account.getClientIdNumber())
          .flatMap(debitor -> {
            if (debitor == true) {
              return Mono.empty();
            } else {
              return accountService.validateClientIdNumber(account.getClientIdNumber());
            }
          })
          .flatMap(accountFound -> {
            if (accountFound.getClientIdNumber() != null) {
              LOGGER.info("La cuenta encontrada es: " + accountFound.getClientIdNumber());
              return Mono.empty();
            } else {
              LOGGER.info("No se encontró la cuenta ");
              return accountService.saveAccount(account);
            }
          });


  }

  /**
   * save account mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> deleteSavingAccount(ServerRequest request) {

    String id = request.pathVariable("id");

    Mono<Account> accountMono = accountService.getAccountById(id);
    return accountMono
          .doOnNext(c -> LOGGER.info("deleteConsumption: consumptonId={}", c.getId()))
          .flatMap(c -> accountService.deleteAccount(id).then(ServerResponse.noContent().build()))
          .switchIfEmpty(ServerResponse.notFound().build());
  }

  /**
   * Update saving account mono.
   *
   * @param request the request
   * @return the mono
   */
  public Mono<ServerResponse> updateAccount(ServerRequest request){
    Mono<Account> accountMono = request.bodyToMono(Account.class);
    String id = request.pathVariable("id");
    return accountService.getAccountById(id).zipWith(accountMono, (db,req) -> {
            db.setAmount(req.getAmount());
            db.setMovementPerMonth((req.getMovementPerMonth()));
            return db;
          }).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountService.update(c),Account.class))
          .switchIfEmpty(ServerResponse.notFound().build());
  }
}
