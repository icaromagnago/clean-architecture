package com.omni.department.api.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.domain.department.usecase.CreateDepartmentUseCase;
import com.omni.department.api.repository.facade.DepartmentRepositoryFacade;

@Service
public class CreateDepartmentService implements CreateDepartmentUseCase {
	
	@Autowired
	DepartmentRepositoryFacade departmentRepositoryFacade;

	@Override
	public DepartmentEntity execute(DepartmentEntity department) {
		return departmentRepositoryFacade.save(department);
	}

}
