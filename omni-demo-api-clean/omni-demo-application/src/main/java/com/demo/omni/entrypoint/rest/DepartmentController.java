package com.demo.omni.entrypoint.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api for department")
public interface DepartmentController {
	
	@ApiOperation("List all departments ordered by ascending code")
	@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully")
	ResponseEntity<BaseResponseDto> list();
	
	@ApiOperation("Get department by id")
	@ApiResponses({
			@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully"),
			@ApiResponse(code = 404, response = BaseResponseDto.class, message = "Not found")
	})
	public ResponseEntity<BaseResponseDto> findById(
			@ApiParam(value = "department's id", required = true, example = "23") Integer id);
		
	@ApiOperation("Deletes a department by its id")
	@ApiResponses({
		@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully"),
		@ApiResponse(code = 404, response = BaseResponseDto.class, message = "Not found")
	})
	public ResponseEntity<BaseResponseDto> remove(
			@ApiParam(value = "department's id", required = true, example = "23") Integer id);
	
	@ApiOperation("Creates a new department")
	@ApiResponses({
		@ApiResponse(code = 201, response = BaseResponseDto.class, message = "Process executed successfully"),
		@ApiResponse(code = 422, response = BaseResponseDto.class, message = "Not found")
	})
	ResponseEntity<BaseResponseDto> create(@ApiParam("Department to be persisted") CreateDepartmentCommand createDepartmentCommand, 
			HttpServletResponse response) throws DepartmentCodeAlreadyExists;

}
