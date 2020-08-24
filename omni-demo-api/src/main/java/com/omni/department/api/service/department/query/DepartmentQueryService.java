package com.omni.department.api.service.department.query;

import java.util.List;

import com.omni.department.api.model.DepartmentEntity;

public interface DepartmentQueryService {
	
	List<DepartmentEntity> findAll();
	
	DepartmentEntity findById(Integer id);
}
