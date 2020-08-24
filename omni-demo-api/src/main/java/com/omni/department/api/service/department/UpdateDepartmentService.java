package com.omni.department.api.service.department;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.domain.department.usecase.UpdateDepartmentUseCase;
import com.omni.department.api.repository.facade.DepartmentRepositoryFacade;

@Service
public class UpdateDepartmentService implements UpdateDepartmentUseCase {
	
	@Autowired
	DepartmentRepositoryFacade departmentRepositoryFacade;

	@Override
	public DepartmentEntity execute(Integer id, DepartmentEntity department) {
		DepartmentEntity persistedDepartment = departmentRepositoryFacade.findById(id);
		
		if (persistedDepartment == null) {
			throw new EmptyResultDataAccessException(String .format("no department with id %s found for update", id), 1);
		}
		
		BeanUtils.copyProperties(department, persistedDepartment, "id");
		
		return departmentRepositoryFacade.save(persistedDepartment);
	}

}
