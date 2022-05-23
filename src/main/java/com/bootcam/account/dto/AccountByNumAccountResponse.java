package com.bootcam.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountByNumAccountResponse {
  
	
	private String  numAccount;
	private Integer  tyAccount;       // 1:ct.Ahorro - 2:ct.Corriente - 3:ct.PlazoFijo
    private String  tyCustomer;       // Persona - Empresarial (persona natural o juridica)
    private String  condition;        // activo - inactivo
    private String  subTyCustomer;    // VIP - PYME
    
    private Double commission;        // comision de mantenimiento
	private Integer movementTrxMax;   // limite maximo de movimientos mensuales
    private Integer movement;         // cantidad de movimientos actuales
    private Double balance;           // Saldo de cliente
    
}
