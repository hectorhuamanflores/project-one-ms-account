package com.bootcam.account.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcam.account.entity.Account;

import reactor.core.publisher.Flux;

@Repository
public interface AccountRepository extends ReactiveCrudRepository <Account,String> {
	
	Flux<Account> findByDocumentNumber(Integer documentNumber);
}
