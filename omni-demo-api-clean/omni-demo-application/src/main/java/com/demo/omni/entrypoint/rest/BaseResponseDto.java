package com.demo.omni.entrypoint.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Response Object", description = "Gives detailed information about the operation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponseDto {
	
	@ApiModelProperty(position = 0, value = "Response status", example = "Bad request")
	private String status;
	
	@ApiModelProperty(position = 1, value = "Detailed response messages", example = "['code is required', 'name is required']")
	private List<String> messages = new ArrayList<String>();
	
	@ApiModelProperty(position = 2, value = "The operation's result")
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
	
	public static ResponseEntity<BaseResponseDto> toOkResponse(String message, Object data) {
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(),
				List.of(message), data));		
	}
	
	public static ResponseEntity<BaseResponseDto> toNotFoundResponse(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new BaseResponseDto(HttpStatus.NOT_FOUND.getReasonPhrase(), List.of(message), null));
			
	} 
	
}
