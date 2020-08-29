package com.demo.omni.core.usecase.impl;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.command.mapper.DepartmentCommandMapper;
import com.demo.omni.core.usecase.dto.CreatedDepartmentDto;
import com.demo.omni.core.usecase.dto.mapper.DepartmentMapper;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExists;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateDepartmentImpl implements CreateDepartment {
	
	private final DepartmentRepository departmentRepository;

	@Override
	public CreatedDepartmentDto execute(CreateDepartmentCommand command) throws DepartmentCodeAlreadyExists {
		if (departmentRepository.doesDepartmentCodeExists(command.getCode())) {
			throw new DepartmentCodeAlreadyExists(String.format("Department code %s already exists!", command.getCode()));
		}
		
		var department = DepartmentCommandMapper.toDepartment(command);
		
		return DepartmentMapper.toCreatedDepartmentDto(departmentRepository.save(department));
	}

}
