package com.wendel.DesafioPicpay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wendel.DesafioPicpay.dtos.UserDTO;
import com.wendel.DesafioPicpay.models.User;
import com.wendel.DesafioPicpay.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(UserDTO userDTO) {
		
		boolean emailAlreadyExists = userRepository.existsByEmail(userDTO.email());
		
		if (emailAlreadyExists) {
			throw new RuntimeException("Esse email já existe.");
		}
		
		boolean documentAlreadyExists = userRepository.existsByDocument(userDTO.document());

		if (documentAlreadyExists) {
			throw new RuntimeException("Esse documento já existe.");
		}		
		User user = new User(userDTO);
		user.setPassword(passwordEncoder.encode(userDTO.password()));
		userRepository.save(user);
		
		return user;
	}
	
}
