package com.bootcamp.bankaccount.service.Impl;

import com.bootcamp.bankaccount.models.dto.ClientQuery;
import com.bootcamp.bankaccount.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private WebClient webClient;

    private static final Logger LOGGER= LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public Mono<ClientQuery> getClient(String clientIdNumber) {
        return webClient.get()
                .uri("/findClientCredit/"+clientIdNumber)
                .retrieve()
                .bodyToMono(ClientQuery.class)
                .doOnNext(c->LOGGER.info("Client response : {}",c.getName()));
    }
}
