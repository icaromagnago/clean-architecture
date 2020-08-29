package com.demo.omni.core.usecase;

import com.demo.omni.core.usecase.command.UpdateDepartmentCommand;
import com.demo.omni.core.usecase.dto.DepartmentDto;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;
import com.demo.omni.core.usecase.exception.DepartmentNotFoundException;

public interface UpdateDepartment {
	
	DepartmentDto execute(Integer id, UpdateDepartmentCommand command) throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException;

}
