package com.omni.department.api.controller.department;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omni.department.api.controller.BaseResponseDto;
import com.omni.department.api.controller.department.dto.CreateDepartmentDto;
import com.omni.department.api.controller.department.dto.CreatedDepartmentDto;
import com.omni.department.api.controller.department.dto.ListDepartmentDto;
import com.omni.department.api.controller.department.dto.QueryDepartmentDto;
import com.omni.department.api.controller.department.dto.UpdateDepartmentDto;
import com.omni.department.api.controller.department.dto.UpdatedDepartmentDto;
import com.omni.department.api.event.CreatedResourceEvent;
import com.omni.department.api.model.DepartmentEntity;
import com.omni.department.api.service.department.command.DepartmentCommandService;
import com.omni.department.api.service.department.query.DepartmentQueryService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentQueryService departmentQueryService;
	
	@Autowired
	private DepartmentCommandService departmentCommandService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<BaseResponseDto> list() {
		List<DepartmentEntity> departments = departmentQueryService.findAll();
		
		List<ListDepartmentDto> departmentsDto = departments.stream()
				.map(department -> modelMapper.map(department, ListDepartmentDto.class))
				.collect(toList());
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), departmentsDto));
	}
	
	@PostMapping
	public ResponseEntity<BaseResponseDto> create(@Valid @RequestBody CreateDepartmentDto createDepartmentDto, HttpServletResponse response) {
		DepartmentEntity department = departmentCommandService.create(convertToEntity(createDepartmentDto));
		
		CreatedDepartmentDto departmentDto = modelMapper.map(department, CreatedDepartmentDto.class);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, department.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new BaseResponseDto(HttpStatus.CREATED.getReasonPhrase(), List.of(getSuccessMessage()), departmentDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponseDto> update(@PathVariable Integer id, @Valid @RequestBody UpdateDepartmentDto updateDepartmentDto) {
		DepartmentEntity updatedDepartment = departmentCommandService.update(id, convertToEntity(updateDepartmentDto));
		
		UpdatedDepartmentDto updatedDepartmentDto = modelMapper.map(updatedDepartment, UpdatedDepartmentDto.class);
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), updatedDepartmentDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponseDto> findById(@PathVariable Integer id) {
		var department = departmentQueryService.findById(id);
		
		var queryDepartmentDto = modelMapper.map(department, QueryDepartmentDto.class);
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), queryDepartmentDto));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<BaseResponseDto> remove(@PathVariable Integer id) {
		departmentCommandService.remove(id);
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage())));
	}
	
	private DepartmentEntity convertToEntity(Object departmentDto) {
		return modelMapper.map(departmentDto, DepartmentEntity.class);
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}
	
}
