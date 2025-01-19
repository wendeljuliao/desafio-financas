package com.wendel.DesafioPicpay.dtos;

import com.wendel.DesafioPicpay.enums.UserTypeEnum;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UserDTO(
		@NotBlank
		String fullname,
		@NotBlank
		String document,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String password,
		@PositiveOrZero
		@NotNull
		Double amount,
		@NotNull
		UserTypeEnum userType
	) {

}
