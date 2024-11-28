package com.challenge.fastfood.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLunchStatusRequest {

	private Long numberLunch;
	private String status;

}
