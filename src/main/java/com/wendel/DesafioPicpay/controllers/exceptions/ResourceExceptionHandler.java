package com.wendel.DesafioPicpay.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wendel.DesafioPicpay.services.exceptions.EntityNotFoundException;
import com.wendel.DesafioPicpay.services.exceptions.InsuficientAmountException;
import com.wendel.DesafioPicpay.services.exceptions.LojistaMustNotTransferException;
import com.wendel.DesafioPicpay.services.exceptions.RecordAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getServletPath());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(InsuficientAmountException.class)
	public ResponseEntity<StandardError> insuficientAmount(InsuficientAmountException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Quantidade insuficiente");
		err.setMessage(e.getMessage());
		err.setPath(request.getServletPath());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(LojistaMustNotTransferException.class)
	public ResponseEntity<StandardError> lojistaMustNotTransfer(LojistaMustNotTransferException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Lojista não pode transferir");
		err.setMessage(e.getMessage());
		err.setPath(request.getServletPath());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(RecordAlreadyExistsException.class)
	public ResponseEntity<StandardError> recordAlreadyExists(RecordAlreadyExistsException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Registro já existente");
		err.setMessage(e.getMessage());
		err.setPath(request.getServletPath());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
