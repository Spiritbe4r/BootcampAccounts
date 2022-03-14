package com.bootcamp.bankaccount.service.Impl;

import com.bootcamp.bankaccount.dto.Credit;
import com.bootcamp.bankaccount.dto.CreditCard;
import com.bootcamp.bankaccount.service.CreditService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootcamp.bankaccount.util.Constants.API_CREDIT_URL;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

    private static final Logger LOGGER= LoggerFactory.getLogger(CreditServiceImpl.class);

    private final WebClient creditWebClient;

    public CreditServiceImpl(){
        this.creditWebClient=WebClient.builder()
                .baseUrl(API_CREDIT_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    @Override
    public Flux<Credit> getCredit(String clientIdNumber) {
        Map<String ,Object> params =new HashMap<String,Object>();
        LOGGER.info("Initializing credit query");
        params.put("clientIdNumber",clientIdNumber);
        return creditWebClient.get()
                .uri("client/{clientIdNumber}",clientIdNumber)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Credit.class))
                .switchIfEmpty(Flux.just(Credit.builder()
                        .debitor(false).build()))
                .doOnNext(c-> LOGGER.info("Credit REsponse : Contract= {}",c.getContractNumber()));

    }


}
