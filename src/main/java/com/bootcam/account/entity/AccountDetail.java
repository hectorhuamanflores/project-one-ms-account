package com.bootcam.account.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(value = "PRODUCTS_ACCOUNT_DETAIL")
public class AccountDetail {
   
	@Id
	private String id;
	private Integer tyAccount;         // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
	private String nameTyAccount;     // ct.Ahorro - ct.Corriente - ct.PlazoFijo
	private Double commission;        // comision de mantenimiento
	private Integer movementTrxMax;   // limite maximo de movimientos mensuales
	
}
