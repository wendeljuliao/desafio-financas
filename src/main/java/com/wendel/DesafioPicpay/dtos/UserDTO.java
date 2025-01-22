package com.wendel.DesafioPicpay.dtos;

import com.wendel.DesafioPicpay.enums.RoleName;
import com.wendel.DesafioPicpay.enums.UserTypeEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UserDTO(
		@NotBlank(message = "Nome completo vazio.")
		@NotNull(message = "Nome completo não pode ser nulo.")
		String fullname,
		@NotBlank(message = "Documento vazio.")
		@NotNull(message = "Documento não pode ser nulo.")
		String document,
		@Email(message = "Email inválido.")
		String email,
		@NotBlank(message = "Senha vazia.")
		@NotNull(message = "Senha não pode ser nula.")
		String password,
		@PositiveOrZero(message = "Campo tem que ser maior ou igual a 0.")
		@NotNull(message = "Quantidade não pode ser nula.")
		Double amount,
		@NotNull(message = "Tipo de usuário não pode ser nulo.")
		UserTypeEnum userType,
		@NotNull(message = "Role não pode ser nula.")
		RoleName role
	) {

}
