package com.wendel.DesafioPicpay.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
		@Email(message = "Email inválido.")
		String email,
		@NotBlank(message = "Senha vazia.")
		@NotNull(message = "Senha não pode ser nula.")
		String password
	) {

}
