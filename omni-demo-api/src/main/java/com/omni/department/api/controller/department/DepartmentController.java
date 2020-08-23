package com.omni.department.api.controller.department;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omni.department.api.controller.BaseResponseDto;
import com.omni.department.api.controller.department.dto.CreateDepartmentDto;
import com.omni.department.api.controller.department.dto.CreatedDepartmentDto;
import com.omni.department.api.controller.department.dto.DepartmentQueryDto;
import com.omni.department.api.model.DepartmentEntity;
import com.omni.department.api.service.department.command.DepartmentCommandService;
import com.omni.department.api.service.department.query.DepartmentQueryService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	private static final String SUCCESS_MESSAGE = "Process executed successfully";
	
	@Autowired
	private DepartmentQueryService departmentQueryService;
	
	@Autowired
	private DepartmentCommandService departmentCommandService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<DepartmentQueryDto> list() {
		
		List<DepartmentEntity> departments = departmentQueryService.findAll();
		
		List<DepartmentQueryDto> departmentsDto = departments.stream()
				.map(department -> modelMapper.map(department, DepartmentQueryDto.class))
				.collect(toList());
		
		return departmentsDto;
	}
	
	@PostMapping
	public ResponseEntity<CreatedDepartmentDto> create(@Valid @RequestBody CreateDepartmentDto createDepartmentDto) {
		
		DepartmentEntity department = departmentCommandService.create(convertToEntity(createDepartmentDto));
		
		return ResponseEntity.ok(modelMapper.map(department, CreatedDepartmentDto.class));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BaseResponseDto> remove(@PathVariable Integer id) {
		departmentCommandService.remove(id);
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(SUCCESS_MESSAGE)));
	}
	
	private DepartmentEntity convertToEntity(CreateDepartmentDto createDepartmentDto) {
		return modelMapper.map(createDepartmentDto, DepartmentEntity.class);
	}
	
}
