package com.bootcam.account.service.impl;

import org.springframework.stereotype.Service;

import com.bootcam.account.dto.AccountByNumAccountResponse;
import com.bootcam.account.dto.UpdateAccountTrxRequest;
import com.bootcam.account.entity.Account;
import com.bootcam.account.repository.AccountRepository;
import com.bootcam.account.service.AccountDetailService;
import com.bootcam.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	private  final AccountRepository accountRepository;
	private  final AccountDetailService accountDetailService;
	
    @Override
    public Flux<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<AccountByNumAccountResponse> getAccountById(String numAccount) {
    	
        return accountRepository.findById(numAccount)
        		 .flatMap(r -> {
        			log.info("getNumAccount"+r.getNumAccount());
        			log.info("getTyAccount"+r.getTyAccount());
        	     return accountDetailService.getAccountByTyAccount(r.getTyAccount())
        	    		 .flatMap(f -> {
        	    			 log.info("getTyAccount"+r.getTyAccount());
        	    			 log.info("getCommission"+f.getCommission());
        	    			 AccountByNumAccountResponse data = AccountByNumAccountResponse.builder()
        	    			    .numAccount(r.getNumAccount())
        	    				.tyAccount(r.getTyAccount())
        	    			    .tyCustomer(r.getTyCustomer())
        	    			    .condition(r.getCondition())
        	    			    .subTyCustomer(r.getSubTyCustomer())
        	    			    .commission(f.getCommission())
        	    				.movementTrxMax(f.getMovementTrxMax())
        	    			    .movement(r.getMovement())
        	    			    .balance(r.getBalance())
        	    			    .build();
        	    		  return Mono.just(data);
        	     });
        	
        });
    }
    
    @Override
	public Flux<Account> getAccountByDocumentNumber(Integer numDoc) {
		log.error("INICIO_Account_DOCUMENT");
		log.info("numDoc: "+numDoc);
		return accountRepository.findByDocumentNumber(numDoc);
	}
    
    @Override
    public Mono<Account> createAccount(Account account) {
    	if(account !=null) {
    		log.error("INICIO_CREACION_Account");
    		log.info("documentNumber: "+account.getDocumentNumber());
    		return accountRepository.save(account);
    	}else {
    		log.error("Account is null");
    		throw new RuntimeException("Account is null");
    	}
       
    }
    
    @Override
    public Mono<Account> updateAccount(Account account) {
    	
        
        return accountRepository.findById(account.getNumAccount())
                .flatMap( object ->{
                	object.setDocumentNumber(account.getDocumentNumber());
                	object.setTyAccount(account.getTyAccount());
                	object.setTyCustomer(account.getTyCustomer());
                	object.setDateStar(account.getDateStar());
                	object.setDateEnd(account.getDateEnd());
                	object.setCondition(account.getCondition());
                	object.setBalance(account.getBalance());
                	object.setMovement(account.getMovement());
                    return accountRepository.save(object);
                 });
    }

    @Override
    public Mono<Account> deleteAccount(String numAccount) {
        return accountRepository.findById(numAccount)
                .flatMap(existsAccountRepository -> accountRepository.delete(existsAccountRepository)
                        .then(Mono.just(existsAccountRepository)));
    }

	@Override
	public Mono<Account> updateAccountTrx(UpdateAccountTrxRequest trx) {
		log.error("INICIO_CREDIT_DOCUMENT");
		log.info("idCredit: "+trx.getNumAccount());
		log.info("type: "+trx.getType());
		log.info("amount: "+trx.getAmount());
		return accountRepository.findById(trx.getNumAccount())
	                .flatMap( object ->{
	                	object.setBalance(object.getBalance()+trx.getAmount()*trx.getType());
	                	object.setMovement(object.getMovement()+1);
	                    return accountRepository.save(object);
	                });
		
	}

	
}
