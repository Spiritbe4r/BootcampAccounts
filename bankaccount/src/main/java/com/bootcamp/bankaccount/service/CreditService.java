package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.models.dto.Credit;
import com.bootcamp.bankaccount.models.dto.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

  /**
   * validate the debitor estate of a client by clientIdNumber mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Mono<Boolean> validateDebtorCredit(String clientIdNumber);

  /**
   * Find by client credit by clientIdNumber mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Flux<Credit> getCredit(String clientIdNumber);

  /**
   * Find by credit card by clientIdNumber mono.
   *
   * @param clientIdNumber the client identity number
   * @return the mono
   */
  Mono<CreditCard> getCreditCard(String clientIdNumber);
}
