package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.dto.ClientQuery;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ClientQuery> getClient(String clientIdNumber);
}
