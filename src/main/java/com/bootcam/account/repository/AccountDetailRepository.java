package com.bootcam.account.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcam.account.entity.AccountDetail;

import reactor.core.publisher.Mono;

@Repository
public interface AccountDetailRepository extends ReactiveCrudRepository <AccountDetail,String>{
	
	Mono<AccountDetail> findByTyAccount(Integer tyAccount);

}
