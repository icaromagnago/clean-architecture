package com.omni.department.api.service.department.command;

import com.omni.department.api.model.DepartmentEntity;

public interface DepartmentCommandService {
	
	DepartmentEntity create(DepartmentEntity department);
	
	void remove(Integer id);
}
