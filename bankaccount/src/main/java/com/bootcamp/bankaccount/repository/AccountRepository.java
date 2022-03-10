package com.bootcamp.bankaccount.repository;

import com.bootcamp.bankaccount.bean.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Configuration
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}
