package com.omni.department.api.domain.department.usecase;

import com.omni.department.api.domain.department.DepartmentEntity;

public interface FindDepartmentByIdUseCase {
	
	DepartmentEntity execute(Integer id);
}
