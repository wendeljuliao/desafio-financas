package com.wendel.DesafioPicpay.dtos;

import java.util.List;

import com.wendel.DesafioPicpay.enums.UserTypeEnum;
import com.wendel.DesafioPicpay.models.Role;
import com.wendel.DesafioPicpay.models.User;

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
	
	public UserResponseDTO(User user) {
		this.fullname = user.getFullname();
		this.document = user.getDocument();
		this.email = user.getEmail();
		this.amount = user.getAmount();
		this.userType = user.getUserType();
		this.roles = user.getRoles();
	}
	
}
