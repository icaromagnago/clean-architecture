package com.omni.department.api.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.domain.department.usecase.ListDepartmentsOrderByCodeUseCase;
import com.omni.department.api.repository.facade.DepartmentRepositoryFacade;

public class ListDepartmentsOrderByCodeService implements ListDepartmentsOrderByCodeUseCase {
	
	@Autowired
	private DepartmentRepositoryFacade departmentRepositoryFacade;

	@Override
	public List<DepartmentEntity> execute() {
		return departmentRepositoryFacade.findAllOrderByCode();		
	}

}
