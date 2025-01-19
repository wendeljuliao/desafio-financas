package com.wendel.DesafioPicpay.models;

import com.wendel.DesafioPicpay.dtos.TransactionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
	
	public Transaction(TransactionDTO transactionDTO) {
		this.amount = transactionDTO.amount();
		this.payerId = transactionDTO.payerId();
		this.payeeId = transactionDTO.payeeId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false)
	private Long payerId;

	@Column(nullable = false)
	private Long payeeId;
	
}
