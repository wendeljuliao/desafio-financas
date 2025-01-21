package com.wendel.DesafioPicpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.wendel.DesafioPicpay.dtos.AuthorizeRequestDTO;
import com.wendel.DesafioPicpay.dtos.TransactionDTO;
import com.wendel.DesafioPicpay.enums.UserTypeEnum;
import com.wendel.DesafioPicpay.models.Transaction;
import com.wendel.DesafioPicpay.models.User;
import com.wendel.DesafioPicpay.repositories.TransactionRepository;
import com.wendel.DesafioPicpay.repositories.UserRepository;
import com.wendel.DesafioPicpay.services.exceptions.EntityNotFoundException;
import com.wendel.DesafioPicpay.services.exceptions.InsuficientAmountException;
import com.wendel.DesafioPicpay.services.exceptions.LojistaMustNotTransferException;

@Service
public class TransactionService {
	
	@Value("${url-util-devi}")
	private String urlUtilDevi;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Transactional
	public Transaction makeTransaction(TransactionDTO transactionDTO) {
		User payer = userRepository.findById(transactionDTO.payerId())
				.orElseThrow(() -> new EntityNotFoundException("Pagador não encontrado."));
		
		
		User payee = userRepository.findById(transactionDTO.payeeId())
				.orElseThrow(() -> new EntityNotFoundException("Beneficiário não encontrado."));
		
		if (payer.getUserType().equals(UserTypeEnum.LOJISTA)) {
			throw new LojistaMustNotTransferException("Lojista não podem realizar transferências.");
		}
		
		if (payer.getAmount() < transactionDTO.amount()) {
			throw new InsuficientAmountException("Saldo insuficiente para realizar a transferência.");
		}
		
		AuthorizeRequestDTO response;
		
		try {
			response =  restTemplate.getForObject(urlUtilDevi + "v2/authorize", AuthorizeRequestDTO.class);
		} catch (Exception e) {
			throw new RuntimeException("Erro na autorização do serviço.");
		}
		
		if (!response.getData().getAuthorization()) {
			throw new RuntimeException("Autorização negada.");
		}
				
		payer.setAmount(payer.getAmount() - transactionDTO.amount());
		payee.setAmount(payee.getAmount() + transactionDTO.amount());
		
		Transaction transaction = new Transaction(transactionDTO);
		transactionRepository.save(transaction);
		
		emailService.enviarEmail();
		
		return transaction;
	}
	
}
