package com.bootcam.account.service;

import com.bootcam.account.dto.AccountByNumAccountResponse;
import com.bootcam.account.dto.UpdateAccountTrxRequest;
import com.bootcam.account.entity.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    public Flux<Account> getAllAccount();
    public Mono<AccountByNumAccountResponse> getAccountById(String numAccount);   // num cuenta bancaria
    public Mono<Account> createAccount(Account Account);
    public Mono<Account> updateAccount(Account Account);
    public Mono<Account> deleteAccount(String numAccount);
    public Flux<Account> getAccountByDocumentNumber(Integer numdoc);
    
    
    public Mono<Account> updateAccountTrx(UpdateAccountTrxRequest trx); // deposito - retiros

}
