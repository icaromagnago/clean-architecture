package com.omni.department.api.domain.department.usecase;

import com.omni.department.api.domain.department.DepartmentEntity;

public interface UpdateDepartmentUseCase {
	
	DepartmentEntity execute(Integer id, DepartmentEntity department);

}
