package com.demo.omni.entrypoint.rest;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api("Rest API for state")
public interface StateController {
	
	@ApiOperation("List all states in alphabetical order")
	@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully")
	ResponseEntity<BaseResponseDto> list();
}
