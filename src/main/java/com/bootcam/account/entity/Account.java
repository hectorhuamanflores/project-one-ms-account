package com.bootcam.account.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(value = "PRODUCTS_ACCOUNT")
public class Account {
	
	    @Id
	    private String  numAccount;       // Numero de cuenta bancaria
	    private Integer documentNumber;   // Dni - Ruc del cliente
	    private Integer  tyAccount;        // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
	    private String  nameTyAccount;    // Ahorro - Corriente - PlazoFijo
	    private String  tyCustomer;       // Personal - Empresarial (persona natural o juridica)
	    private String  subTyCustomer;    // VIP - PYME
	    @JsonFormat(pattern="yyyy-MM-dd")
	    private LocalDate     dateStar;   // Fecha de creacion
	    @JsonFormat(pattern="yyyy-MM-dd")
	    private LocalDate     dateEnd;    // Fecha de caducidad
	    private String condition;         // activo - inactivo
		private Double  balance;          // saldo de la cuenta bancaria
		private Integer movement;         // numero de movimiento actuales
		
		
		
}
