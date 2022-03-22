package com.bootcamp.bankaccount.service.Impl;

import com.bootcamp.bankaccount.models.dto.Credit;
import com.bootcamp.bankaccount.models.dto.CreditCard;
import com.bootcamp.bankaccount.service.CreditService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Value("${microservices-urls.api-credit}")
  private String apiCredit;

  @Value("${microservices-urls.api-creditcard}")
  private String apiCreditcard;

  @Autowired
  private WebClient webClient;

  public CreditServiceImpl() {
    this.webClient = WebClient.builder()
          .baseUrl(apiCredit)
          .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .build();
  }


  @Override
  public Flux<Credit> getCredit(String clientIdNumber) {
    Map<String, Object> params = new HashMap<String, Object>();
    LOGGER.info("Initializing credit query");

    params.put("clientIdNumber", clientIdNumber);
    return webClient

          .get()
          .uri("/client/{clientIdNumber}", clientIdNumber)
          .accept(MediaType.APPLICATION_JSON)
          .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Credit.class))
          .doOnNext(cr -> LOGGER.info("Credit REsponse : Credit= {}", cr.toString()))
          .switchIfEmpty(Flux.just(Credit.builder()
                .debitor(false).build()))
          .doOnNext(c -> LOGGER.info("Credit REsponse : Contract= {}", c.getContractNumber()));

  }

  @Override
  public Mono<CreditCard> getCreditCard(String clientIdNumber) {
    Map<String, Object> params = new HashMap<String, Object>();
    LOGGER.info("initializing creditcard query");
    params.put("clientIdNumber", clientIdNumber);
    LOGGER.info("PARAMS " + params);
    return WebClient.builder()
          .baseUrl(apiCreditcard)
          .build()
          .get()
          .uri("/client/{clientIdNumber}", clientIdNumber)
          .accept(MediaType.APPLICATION_JSON)
          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CreditCard.class))
          .switchIfEmpty(Mono.just(CreditCard.builder()
                .debitor(false).build()))
          .doOnNext(c -> LOGGER.info("CreditCard Response: Pan={}", c.getPan()));
  }


  @Override
  public Mono<Boolean> validateDebtorCredit(String clientIdNumber) {

    Mono<List<Credit>> credit = getCredit(clientIdNumber)
          .filter(creditFound -> creditFound.isDebitor()).collectList();
    Mono<CreditCard> creditcard = getCreditCard(clientIdNumber);

    return Mono.zip(credit, creditcard, (a, b) -> {

      if (a.size() > 0) {
        return true;
      } else if (b.isDebitor()) {
        return true;
      } else
        return false;
    }).doOnNext(value -> LOGGER.info("the value of Debitor validate is: {}" + value));


  }


}
