package com.bootcamp.bankproduct.service;

import com.bootcamp.bankproduct.dto.AccountDto;
import com.bootcamp.bankproduct.repository.AccountRepository;
import com.bootcamp.bankproduct.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

@Autowired
    private AccountRepository repository;

    public Flux<AccountDto> getClients(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<AccountDto> getClient(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<AccountDto> saveClients(Mono<AccountDto> AccountDtoMono){
        return AccountDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<AccountDto> updateClients(Mono<AccountDto> accountDtoMono,String id){
        return repository.findById(id)
                .flatMap(p->accountDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteClient(String id){
        return repository.deleteById(id);
    }
}
