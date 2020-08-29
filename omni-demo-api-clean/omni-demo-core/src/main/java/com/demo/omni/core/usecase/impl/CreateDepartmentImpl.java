package com.demo.omni.core.usecase.impl;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.command.mapper.DepartmentCommandMapper;
import com.demo.omni.core.usecase.dto.DepartmentDto;
import com.demo.omni.core.usecase.dto.mapper.DepartmentMapper;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateDepartmentImpl implements CreateDepartment {
	
	private final DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto execute(CreateDepartmentCommand command) throws DepartmentCodeAlreadyExistsException {
		if (departmentRepository.doesDepartmentCodeExists(command.getCode())) {
			throw new DepartmentCodeAlreadyExistsException(String.format("Department code %s already exists!", command.getCode()));
		}
		
		var department = DepartmentCommandMapper.toDepartment(command);
		
		return DepartmentMapper.toDepartmentDto(departmentRepository.save(department));
	}

}
