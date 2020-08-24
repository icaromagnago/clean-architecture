package com.omni.department.api.domain.department.usecase;

import java.util.List;

import com.omni.department.api.domain.department.DepartmentEntity;

public interface ListDepartmentsOrderByCodeUseCase {
	
	List<DepartmentEntity>  execute();
}
