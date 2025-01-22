package com.wendel.DesafioPicpay.controllers.exceptions;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldsError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private List<String> errors;
	private String path;
	
}
