package com.wendel.DesafioPicpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wendel.DesafioPicpay.dtos.TransactionDTO;
import com.wendel.DesafioPicpay.models.Transaction;
import com.wendel.DesafioPicpay.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.makeTransaction(transactionDTO));
	}

}
