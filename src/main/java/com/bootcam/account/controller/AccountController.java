package com.bootcam.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcam.account.dto.AccountByNumAccountRequest;
import com.bootcam.account.dto.AccountByNumAccountResponse;
import com.bootcam.account.dto.AccountByNumDocRequest;
import com.bootcam.account.dto.UpdateAccountTrxRequest;
import com.bootcam.account.entity.Account;
import com.bootcam.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Account")
public class AccountController {

	private final AccountService accountServiceimpl;
	
    @GetMapping
    public Mono<ResponseEntity<Flux<Account>>>getAllAccount() {
        Flux<Account> list=this.accountServiceimpl.getAllAccount();
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }
    
    @PostMapping("/numAccount")
    public Mono<ResponseEntity<AccountByNumAccountResponse>> getAccountById(@RequestBody AccountByNumAccountRequest accountByNumAccountRequest){
        var Account=this.accountServiceimpl.getAccountById(accountByNumAccountRequest.getNumAccount());
        return Account.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/numberDocument")
    public Mono<ResponseEntity<Flux<Account>>> getAccountByNumDoc(@RequestBody AccountByNumDocRequest accountByNumDocRequest){
    	Flux<Account> list=this.accountServiceimpl.getAccountByDocumentNumber(accountByNumDocRequest.getDocumentNumber());
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> create(@RequestBody Account Account){
        return this.accountServiceimpl.createAccount(Account);
    }

    @PutMapping("/updateAccount")
    public Mono<ResponseEntity<Account>> updateAccount(@RequestBody Account Account){

        return this.accountServiceimpl.updateAccount(Account)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable String id){
        return this.accountServiceimpl.deleteAccount(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/updateAccountTrx")
    public Mono<ResponseEntity<Account>> updateCreditConsumptionPayment(@RequestBody UpdateAccountTrxRequest trx){

        return this.accountServiceimpl.updateAccountTrx(trx)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
   
    
}
