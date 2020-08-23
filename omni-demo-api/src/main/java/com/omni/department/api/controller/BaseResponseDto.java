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
	
	protected String status;
	
	protected List<String> messages = new ArrayList<String>();
	
	public BaseResponseDto(String status) {
		this.status = status;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
	
}
