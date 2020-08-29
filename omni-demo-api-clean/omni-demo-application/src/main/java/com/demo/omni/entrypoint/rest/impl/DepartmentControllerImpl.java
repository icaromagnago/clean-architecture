package com.demo.omni.entrypoint.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.FindDepartmentById;
import com.demo.omni.core.usecase.GetAllDepartmentsOrderByCode;
import com.demo.omni.core.usecase.RemoveDepartment;
import com.demo.omni.core.usecase.UpdateDepartment;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.command.UpdateDepartmentCommand;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;
import com.demo.omni.core.usecase.exception.DepartmentNotFoundException;
import com.demo.omni.entrypoint.rest.BaseResponseDto;
import com.demo.omni.entrypoint.rest.DepartmentController;
import com.demo.omni.entrypoint.rest.dto.DepartmentInputDto;
import com.demo.omni.entrypoint.rest.event.CreatedResourceEvent;

@RestController
@RequestMapping("/departments")
@Validated
public class DepartmentControllerImpl implements DepartmentController {
	
	private MessageSource messageSource;
	private ApplicationEventPublisher publisher;
	private ModelMapper modelMapper;
	
	private GetAllDepartmentsOrderByCode getAllDepartmentsUseCase;
	private FindDepartmentById findDepartmentByIdUseCase;
	private RemoveDepartment removeDepartmentUseCase;
	private CreateDepartment createDepartmentUseCase;
	private UpdateDepartment updateDepartmentUseCase;
	
	public DepartmentControllerImpl(MessageSource messageSource, 
			ApplicationEventPublisher publisher, 
			ModelMapper modelMapper,
			GetAllDepartmentsOrderByCode getAllDepartmentsUseCase,
			FindDepartmentById findDepartmentByIdUseCase,
			RemoveDepartment removeDepartmentUseCase,
			CreateDepartment createDepartmentUseCase,
			UpdateDepartment updateDepartmentUseCase) {
		
		this.messageSource = messageSource;
		this.publisher = publisher;
		this.modelMapper = modelMapper;
		
		this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
		this.findDepartmentByIdUseCase = findDepartmentByIdUseCase;
		this.removeDepartmentUseCase = removeDepartmentUseCase;
		this.createDepartmentUseCase = createDepartmentUseCase;
		this.updateDepartmentUseCase = updateDepartmentUseCase;
	}

	@Override
	@GetMapping
	public ResponseEntity<BaseResponseDto> list() {
		var departments = getAllDepartmentsUseCase.execute();
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), departments));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponseDto> findById(@PathVariable Integer id) {
		var department = findDepartmentByIdUseCase.execute(id);
		
		return department
				.map(depart -> BaseResponseDto.toOkResponse(getSuccessMessage(), depart))
				.orElse(BaseResponseDto.toNotFoundResponse(getNotFoundMessage("Department", id.toString())));		
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponseDto> remove(Integer id) {
		removeDepartmentUseCase.execute(id);
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage())));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<BaseResponseDto> create(@Valid @RequestBody DepartmentInputDto createDepartmentDto,
			HttpServletResponse response) throws DepartmentCodeAlreadyExistsException {
		
		var command = modelMapper.map(createDepartmentDto, CreateDepartmentCommand.class);
		var departmentDto = createDepartmentUseCase.execute(command);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, departmentDto.getId()));
		
		return BaseResponseDto.toCreatedResponse(getSuccessMessage(), departmentDto);
	}
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponseDto> update(@PathVariable Integer id, @RequestBody DepartmentInputDto updateDepartmentDto) throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		
		var command = modelMapper.map(updateDepartmentDto, UpdateDepartmentCommand.class);
		var departmentDto = updateDepartmentUseCase.execute(id, command);
		
		return BaseResponseDto.toOkResponse(getSuccessMessage(), departmentDto);
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}
	
	private String getNotFoundMessage(String objectName, String id) {
		String[] args = new String[] {objectName, id};
		return messageSource.getMessage("notfound.message", args, LocaleContextHolder.getLocale());
	}
}
