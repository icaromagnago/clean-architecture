package com.demo.omni.core.usecase;

import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.dto.CreatedDepartmentDto;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExists;

public interface CreateDepartment {
	
	CreatedDepartmentDto execute(CreateDepartmentCommand command) throws DepartmentCodeAlreadyExists;
}
