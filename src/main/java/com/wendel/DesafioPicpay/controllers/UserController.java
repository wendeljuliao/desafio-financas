package com.wendel.DesafioPicpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wendel.DesafioPicpay.dtos.LoginUserDTO;
import com.wendel.DesafioPicpay.dtos.RecoveryJWTTokenDTO;
import com.wendel.DesafioPicpay.dtos.UserDTO;
import com.wendel.DesafioPicpay.dtos.UserResponseDTO;
import com.wendel.DesafioPicpay.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<RecoveryJWTTokenDTO> authenticateUser(@RequestBody LoginUserDTO loginUserDTO) {
		RecoveryJWTTokenDTO token = userService.authenticateUser(loginUserDTO);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
	}
}
