package com.wendel.DesafioPicpay.dtos;

import java.util.List;

import com.wendel.DesafioPicpay.enums.UserTypeEnum;
import com.wendel.DesafioPicpay.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	
	private String fullname;
	private String document;
	private String email;
	private Double amount;
	private UserTypeEnum userType;
	private List<Role> roles;
	
}
