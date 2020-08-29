package com.demo.omni.entrypoint.rest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@ApiModel(value = "Dto for create/update a department", description = "")
@Value
public class DepartmentInputDto {
	
	@ApiModelProperty(position = 0, value = "Department's code", example = "996")
	@NotNull(message = "code may not be null")
	@Positive(message = "code must be a positive number")
	private Integer code;
	
	@ApiModelProperty(position = 1, value = "Department's name", example = "Compliance")
	@NotEmpty(message = "name may not be null nor empty")
	@Size(max = 50, message = "max lenght of name is {max}")
	private String name;
	
	@ApiModelProperty(position = 2, value = "Department's local", example = "Paulista Avenue")
	@NotEmpty(message = "local may not be null nor empty")
	@Size(max = 200, message = "max lenght of local is {max}")
	private String local;

	@ApiModelProperty(position = 3, value = "Department's city", example = "Rio de Janeiro")
	@NotEmpty(message = "city may not be null nor empty")
	@Size(max = 50, message = "max lenght of city is {max}")
	private String city;
	
	@ApiModelProperty(position = 4, value = "Department's board", allowableValues = "BUSINESS, EIS, RECOVERY", example = "BUSINESS")
	@NotNull(message = "board may not be null")
	private String board;

	@ApiModelProperty(position = 5, value = "Department's state", example = "state")
	@NotNull(message = "state may not be null")
	@Valid
	private StateDto state;
	
	@ApiModel(value = "Represents a State")
	@Getter
	@NoArgsConstructor
	public static class StateDto {
		
		@ApiModelProperty(position = 0, value = "State's id", example = "25")
		@NotNull(message = "state id may not be null")
		private Integer id;
	}
}
