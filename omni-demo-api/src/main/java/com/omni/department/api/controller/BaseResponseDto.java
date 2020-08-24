package com.omni.department.api.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Response Object", description = "Gives detailed information about the operation")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseResponseDto {
	
	@ApiModelProperty(position = 0, value = "Response status", example = "Bad request")
	private String status;
	
	@ApiModelProperty(position = 1, value = "Detailed response messages", example = "['code is required', 'name is required']")
	private List<String> messages = new ArrayList<String>();
	
	@ApiModelProperty(position = 2, value = "The result can be an object or an list of objects depending on the operation")
	private Object result;
	
	public BaseResponseDto(String status) {
		this.status = status;
	}
	
	public BaseResponseDto(String status, List<String> messages) {
		this.status = status;
		this.messages = messages;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
	
}
