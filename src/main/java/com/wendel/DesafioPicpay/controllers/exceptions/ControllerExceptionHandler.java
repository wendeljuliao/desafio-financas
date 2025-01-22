package com.wendel.DesafioPicpay.controllers.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wendel.DesafioPicpay.services.exceptions.EntityNotFoundException;
import com.wendel.DesafioPicpay.services.exceptions.InsuficientAmountException;
import com.wendel.DesafioPicpay.services.exceptions.LojistaMustNotTransferException;
import com.wendel.DesafioPicpay.services.exceptions.ProcessingServiceErrorException;
import com.wendel.DesafioPicpay.services.exceptions.RecordAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
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
	
	@ExceptionHandler(ProcessingServiceErrorException.class)
	public ResponseEntity<StandardError> processingService(ProcessingServiceErrorException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Erro no processamento do serviço");
		err.setMessage(e.getMessage());
		err.setPath(request.getServletPath());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<FieldsError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		FieldsError err = new FieldsError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Erro no(s) campo(s)");
		err.setPath(request.getServletPath());
		
		List<String> errors = e.getBindingResult().getFieldErrors()
				.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		
		err.setErrors(errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
