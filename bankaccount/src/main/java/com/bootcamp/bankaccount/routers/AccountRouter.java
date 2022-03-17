package com.bootcamp.bankaccount.routers;

import com.bootcamp.bankaccount.handlers.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AccountRouter {

    @Bean
    public RouterFunction<ServerResponse>routes(AccountHandler accountHandler){
        return route(GET("/api/faccounts"),accountHandler::findAll)
                .andRoute(POST("/api/faccounts/{clientIdNumber}"),accountHandler::newSavingAccount)
                .andRoute(DELETE("/api/faccounts/{id}"),accountHandler::deleteSavingAccount);
    }
}
