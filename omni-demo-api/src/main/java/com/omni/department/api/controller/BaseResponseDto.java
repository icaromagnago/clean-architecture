package com.omni.department.api.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseResponseDto {
	
	private String status;
	
	private List<String> messages = new ArrayList<String>();
	
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
