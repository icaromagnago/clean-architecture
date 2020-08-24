package com.omni.department.api.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.department.usecase.RemoveDepartmentUseCase;
import com.omni.department.api.repository.facade.DepartmentRepositoryFacade;

@Service
public class RemoveDepartmentService implements RemoveDepartmentUseCase {
	
	@Autowired
	DepartmentRepositoryFacade departmentRepositoryFacade;

	@Override
	public void execute(Integer id) {
		departmentRepositoryFacade.deleteById(id);
	}

}
