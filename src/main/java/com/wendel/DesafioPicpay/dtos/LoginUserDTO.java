package com.wendel.DesafioPicpay.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(
		@NotBlank
		String email,
		@NotBlank
		String password
	) {

}
