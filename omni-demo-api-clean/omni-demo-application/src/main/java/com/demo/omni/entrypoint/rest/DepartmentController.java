package com.demo.omni.entrypoint.rest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;
import com.demo.omni.core.usecase.exception.DepartmentNotFoundException;
import com.demo.omni.entrypoint.rest.dto.DepartmentInputDto;

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
			@ApiParam(value = "department's id", required = true, example = "23") @PathVariable Integer id);
		
	@ApiOperation("Deletes a department by its id")
	@ApiResponses({
		@ApiResponse(code = 200, response = BaseResponseDto.class, message = "Process executed successfully"),
		@ApiResponse(code = 404, response = BaseResponseDto.class, message = "Not found")
	})
	public ResponseEntity<BaseResponseDto> remove(
			@ApiParam(value = "department's id", required = true, example = "23") @PathVariable Integer id);
	
	@ApiOperation("Creates a new department")
	@ApiResponses({
		@ApiResponse(code = 201, response = BaseResponseDto.class, message = "Process executed successfully"),
		@ApiResponse(code = 422, response = BaseResponseDto.class, message = "Department's code already exists")
	})
	ResponseEntity<BaseResponseDto> create(@ApiParam("Department to be created") @Valid @RequestBody DepartmentInputDto createDepartmentDto, 
			HttpServletResponse response) throws DepartmentCodeAlreadyExistsException;
	
	@ApiOperation("Updates one department")
	@ApiResponses({
		@ApiResponse(code = 201, response = BaseResponseDto.class, message = "Process executed successfully"),
		@ApiResponse(code = 404, response = BaseResponseDto.class, message = "Not found"),
		@ApiResponse(code = 422, response = BaseResponseDto.class, message = "Department's code already exists")
	})
	ResponseEntity<BaseResponseDto> update(@ApiParam("Department's id to be updated") @PathVariable Integer id, 
			@ApiParam("Department to be updated") @RequestBody DepartmentInputDto updateDepartmentDto) 
			throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException;

}
