package com.demo.omni.core.usecase;

import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.dto.DepartmentDto;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;

public interface CreateDepartment {
	
	DepartmentDto execute(CreateDepartmentCommand command) throws DepartmentCodeAlreadyExistsException;
}
