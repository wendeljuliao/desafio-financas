package com.wendel.DesafioPicpay.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransactionDTO(
			@PositiveOrZero(message = "Campo tem que ser maior ou igual a 0.")
			@NotNull(message = "Quantidade não pode ser nula.")
			Double amount,
			@NotNull(message = "Pagador não pode ser nulo")
			Long payerId,
			@NotNull(message = "Beneficiário não pode ser nulo")
			Long payeeId
		) {

}
