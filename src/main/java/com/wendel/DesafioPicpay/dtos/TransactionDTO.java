package com.wendel.DesafioPicpay.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransactionDTO(
			@NotNull
			@PositiveOrZero
			Double amount,
			@NotNull
			Long payerId,
			@NotNull
			Long payeeId
		) {

}
