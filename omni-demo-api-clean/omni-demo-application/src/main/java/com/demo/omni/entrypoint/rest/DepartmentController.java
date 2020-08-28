package com.demo.omni.entrypoint.rest;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api("Api for department")
public interface DepartmentController {
	
	@ApiOperation("List all departments ordered by ascending code")
	@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully")
	ResponseEntity<BaseResponseDto> list();
}
