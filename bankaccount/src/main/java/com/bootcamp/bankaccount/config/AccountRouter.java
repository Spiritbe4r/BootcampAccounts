package com.bootcamp.bankaccount.config;

import com.bootcamp.bankaccount.handlers.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AccountRouter {

  /**
   * Routes router function.
   *
   * @param accountHandler the account handler
   * @return the router function
   */
  @Bean
  public RouterFunction<ServerResponse> routes(AccountHandler accountHandler) {
    return route(GET("/api/account"), accountHandler::findAll)
          .andRoute(POST("/api/account/{clientIdNumber}"), accountHandler::newSavingAccount)
          .andRoute(GET("/api/account/{accountNumber}"), accountHandler::findByAccountNumber)
          .andRoute(DELETE("/api/account/{id}"), accountHandler::deleteSavingAccount);
  }
}
