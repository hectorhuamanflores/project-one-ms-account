package com.bootcam.account.service.impl;

import org.springframework.stereotype.Service;

import com.bootcam.account.entity.Account;
import com.bootcam.account.entity.AccountDetail;
import com.bootcam.account.repository.AccountDetailRepository;
import com.bootcam.account.service.AccountDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountDetailServiceImpl implements AccountDetailService{
	
	private  final AccountDetailRepository accountDetailRepository;
	
	@Override
	public Flux<AccountDetail> getAllAccountDetail() {
		// TODO Auto-generated method stub
		return accountDetailRepository.findAll();
	}

	@Override
	public Mono<AccountDetail> getAccountDetailById(String id) {
		// TODO Auto-generated method stub
		return accountDetailRepository.findById(id);
	}

	@Override
	public Mono<AccountDetail> createAccountDetail(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		if(accountDetail !=null) {
    		return accountDetailRepository.save(accountDetail);
    	}else {
    		log.error("Account is null");
    		throw new RuntimeException("Account is null");
    	}
		
	}

	@Override
	public Mono<AccountDetail> updateAccountDetail(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		return accountDetailRepository.findByTyAccount(accountDetail.getTyAccount())
                .flatMap( object ->{
                	object.setTyAccount(accountDetail.getTyAccount());
                	object.setNameTyAccount(accountDetail.getNameTyAccount());
                	object.setMovementTrxMax(accountDetail.getMovementTrxMax());
                	object.setCommission(accountDetail.getCommission());
                    return accountDetailRepository.save(object);
                 });
	}

	@Override
	public Mono<AccountDetail> deleteAccountDetail(String id) {
		// TODO Auto-generated method stub
		 return accountDetailRepository.findById(id)
	                .flatMap(existsAccountRepository -> accountDetailRepository.delete(existsAccountRepository)
	                        .then(Mono.just(existsAccountRepository)));
	}

	@Override
	public Mono<AccountDetail> getAccountByTyAccount(Integer tyAccount) {
		// TODO Auto-generated method stub
		return accountDetailRepository.findByTyAccount(tyAccount);
	}

}
