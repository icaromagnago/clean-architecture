package com.omni.department.api.controller.department.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.omni.department.api.model.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CreateDepartmentDto {
	
	@NotNull(message = "code may not be null")
	@Positive
	private Integer code;
	
	@NotEmpty(message = "name may not be null nor empty")
	@Size(max = 50)
	private String name;
	
	@NotEmpty(message = "local may not be null nor empty")
	@Size(max = 200)
	private String local;

	@NotEmpty(message = "city may not be null nor empty")
	@Size(max = 50)
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
