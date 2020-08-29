package com.demo.omni.entrypoint.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.FindDepartmentById;
import com.demo.omni.core.usecase.GetAllDepartmentsOrderByCode;
import com.demo.omni.core.usecase.RemoveDepartment;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExists;
import com.demo.omni.entrypoint.rest.BaseResponseDto;
import com.demo.omni.entrypoint.rest.DepartmentController;
import com.demo.omni.entrypoint.rest.event.CreatedResourceEvent;

@RestController
@RequestMapping("/departments")
public class DepartmentControllerImpl implements DepartmentController {
	
	private MessageSource messageSource;
	private ApplicationEventPublisher publisher;
	
	private GetAllDepartmentsOrderByCode getAllDepartmentsUseCase;
	private FindDepartmentById findDepartmentByIdUseCase;
	private RemoveDepartment removeDepartmentUseCase;
	private CreateDepartment createDepartmentUseCase;
	
	public DepartmentControllerImpl(MessageSource messageSource, 
			ApplicationEventPublisher publisher, 
			GetAllDepartmentsOrderByCode getAllDepartmentsUseCase,
			FindDepartmentById findDepartmentByIdUseCase,
			RemoveDepartment removeDepartmentUseCase,
			CreateDepartment createDepartmentUseCase) {
		
		this.messageSource = messageSource;
		this.publisher = publisher;
		this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
		this.findDepartmentByIdUseCase = findDepartmentByIdUseCase;
		this.removeDepartmentUseCase = removeDepartmentUseCase;
		this.createDepartmentUseCase = createDepartmentUseCase;
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
	public ResponseEntity<BaseResponseDto> create(@Valid @RequestBody CreateDepartmentCommand createDepartmentCommand,
			HttpServletResponse response) throws DepartmentCodeAlreadyExists {
		
		var createdDepartmentDto = createDepartmentUseCase.execute(createDepartmentCommand);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, createdDepartmentDto.getId()));
		
		return BaseResponseDto.toOkResponse(getSuccessMessage(), createdDepartmentDto);
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}
	
	private String getNotFoundMessage(String objectName, String id) {
		String[] args = new String[] {objectName, id};
		return messageSource.getMessage("notfound.message", args, LocaleContextHolder.getLocale());
	}
}
