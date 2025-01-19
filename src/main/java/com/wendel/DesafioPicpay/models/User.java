package com.wendel.DesafioPicpay.models;

import com.wendel.DesafioPicpay.dtos.UserDTO;
import com.wendel.DesafioPicpay.enums.UserTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	public User(UserDTO userDTO) {
		this.fullname = userDTO.fullname();
		this.document = userDTO.document();
		this.email = userDTO.email();
		this.password = userDTO.password();
		this.amount = userDTO.amount();
		this.userType = userDTO.userType();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullname;
	
	@Column(nullable = false, unique = true)
	private String document;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false)
	private UserTypeEnum userType;
	
}
