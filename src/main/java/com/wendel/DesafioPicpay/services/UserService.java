package com.wendel.DesafioPicpay.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wendel.DesafioPicpay.configs.SecurityConfiguration;
import com.wendel.DesafioPicpay.configs.authentication.JwtTokenService;
import com.wendel.DesafioPicpay.configs.userdetails.UserDetailsImpl;
import com.wendel.DesafioPicpay.dtos.LoginUserDTO;
import com.wendel.DesafioPicpay.dtos.RecoveryJWTTokenDTO;
import com.wendel.DesafioPicpay.dtos.UserDTO;
import com.wendel.DesafioPicpay.dtos.UserResponseDTO;
import com.wendel.DesafioPicpay.models.User;
import com.wendel.DesafioPicpay.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private SecurityConfiguration securityConfiguration;
	
	public RecoveryJWTTokenDTO authenticateUser(LoginUserDTO loginUserDTO) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.password());
		System.out.println(loginUserDTO.email() + " " + loginUserDTO.password());
		System.out.println(usernamePasswordAuthenticationToken);
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		System.out.println(usernamePasswordAuthenticationToken);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		return new RecoveryJWTTokenDTO(jwtTokenService.generateToken(userDetails));
	}
	
	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream().map(x -> new UserResponseDTO(x.getFullname(), x.getDocument(), x.getEmail(), x.getAmount(), x.getUserType(), x.getRoles())).toList();
	}
	
	public UserResponseDTO createUser(UserDTO userDTO) {
		
		boolean emailAlreadyExists = userRepository.existsByEmail(userDTO.email());
		
		if (emailAlreadyExists) {
			throw new RuntimeException("Esse email já existe.");
		}
		
		boolean documentAlreadyExists = userRepository.existsByDocument(userDTO.document());

		if (documentAlreadyExists) {
			throw new RuntimeException("Esse documento já existe.");
		}
		
		User user = new User(userDTO);
		user.setPassword(securityConfiguration.passwordEncoder().encode(userDTO.password()));
		userRepository.save(user);
		
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		BeanUtils.copyProperties(user, userResponseDTO);
		
		return userResponseDTO;
	}
	
}
