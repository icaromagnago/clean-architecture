package com.omni.department.api.controller.state;

import org.springframework.http.ResponseEntity;

import com.omni.department.api.controller.BaseResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api("Api for state")
public interface StateApi {
	
	@ApiOperation("List all states in alphabetical order")
	@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully")
	ResponseEntity<BaseResponseDto> list();
}
