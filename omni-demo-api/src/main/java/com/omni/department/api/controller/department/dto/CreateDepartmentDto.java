package com.omni.department.api.controller.department.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.omni.department.api.domain.department.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CreateDepartmentDto {
	
	@NotNull(message = "code may not be null")
	@Positive(message = "code must be a positive number")
	private Integer code;
	
	@NotEmpty(message = "name may not be null nor empty")
	@Size(max = 50, message = "max lenght of name is {max}")
	private String name;
	
	@NotEmpty(message = "local may not be null nor empty")
	@Size(max = 200, message = "max lenght of local is {max}")
	private String local;

	@NotEmpty(message = "city may not be null nor empty")
	@Size(max = 50, message = "max lenght of city is {max}")
	private String city;
	
	@NotNull(message = "board may not be null")
	private Board board;

	@NotNull(message = "state may not be null")
	private StateDto state;
	
	@Getter
	@NoArgsConstructor
	public class StateDto {
		
		@NotNull
		private Integer id;
	}
}
