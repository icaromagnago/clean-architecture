package com.omni.department.api.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.domain.department.usecase.FindDepartmentByIdUseCase;
import com.omni.department.api.repository.facade.DepartmentRepositoryFacade;

@Service
public class FindDepartmentByIdService implements FindDepartmentByIdUseCase {
	
	@Autowired
	DepartmentRepositoryFacade departmentRepositoryFacade;

	@Override
	public DepartmentEntity execute(Integer id) {
		DepartmentEntity persistedDepartment = departmentRepositoryFacade.findById(id);
		
		if (persistedDepartment == null) {
			throw new EmptyResultDataAccessException(String .format("no department with id %s found", id), 1);
		}
		
		return persistedDepartment;
	}

}
