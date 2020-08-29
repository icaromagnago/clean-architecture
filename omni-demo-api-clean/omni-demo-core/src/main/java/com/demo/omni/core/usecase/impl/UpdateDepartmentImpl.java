package com.demo.omni.core.usecase.impl;


import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.entity.Department;
import com.demo.omni.core.entity.State;
import com.demo.omni.core.usecase.UpdateDepartment;
import com.demo.omni.core.usecase.command.UpdateDepartmentCommand;
import com.demo.omni.core.usecase.dto.DepartmentDto;
import com.demo.omni.core.usecase.dto.mapper.DepartmentMapper;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;
import com.demo.omni.core.usecase.exception.DepartmentNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDepartmentImpl implements UpdateDepartment {

	private final DepartmentRepository departmentRepository;
	
	@Override
	public DepartmentDto execute(Integer id, UpdateDepartmentCommand command) throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		var departmentOptional = departmentRepository.findById(id);
		
		if (!departmentOptional.isPresent()) {
			throw new DepartmentNotFoundException(String .format("No department with id %s found for update", id));
		}
		
		var persistedDepartment = departmentOptional.get();
		
		verifyDepartmentCodeAlreadyExists(command, persistedDepartment);
		
		persistedDepartment.setCode(command.getCode());
		persistedDepartment.setName(command.getName());
		persistedDepartment.setLocal(command.getLocal());
		persistedDepartment.setCity(command.getCity());
		persistedDepartment.setBoard(command.getBoard());
		persistedDepartment.setState(State.builder().id(command.getState().getId()).build());
		
		var updatedDepartment = departmentRepository.save(persistedDepartment);
		
		return DepartmentMapper.toDepartmentDto(updatedDepartment);
	}

	private void verifyDepartmentCodeAlreadyExists(UpdateDepartmentCommand command, Department persistedDepartment)
			throws DepartmentCodeAlreadyExistsException {
		
		var departmentOptional = departmentRepository.findByCode(command.getCode());
		
		if (departmentOptional.isPresent()) {
			var isTheSameDepartment = departmentRepository.findByCode(command.getCode())
					.filter(departmentFindByCode -> departmentFindByCode.getId().equals(persistedDepartment.getId()))
					.isPresent();
			
			if (!isTheSameDepartment) {
				throw new DepartmentCodeAlreadyExistsException(String.format("Department code %s already exists!", command.getCode()));
			}
		}
	}

}
