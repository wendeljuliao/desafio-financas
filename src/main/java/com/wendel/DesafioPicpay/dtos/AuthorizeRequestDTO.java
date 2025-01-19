package com.wendel.DesafioPicpay.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeRequestDTO {
	
	private String status;
	
	private AuthorizeDataDTO data;
	
}
