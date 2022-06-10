package com.arjunanr.bankapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorApiResponse {

	@JsonProperty("success")
	private Boolean success;

	@JsonProperty("message")
	private String message;
	
}
