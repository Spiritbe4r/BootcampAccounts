package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.dto.ClientQuery;
import reactor.core.publisher.Mono;

public interface ClientService {

  /**
   * Find by client by clientIdNumber mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Mono<ClientQuery> getClient(String clientIdNumber);
}
