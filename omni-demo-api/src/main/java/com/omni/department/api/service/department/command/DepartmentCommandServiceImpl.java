package com.omni.department.api.service.department.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.department.api.model.DepartmentEntity;
import com.omni.department.api.repository.DepartmentRepository;

@Service
public class DepartmentCommandServiceImpl implements DepartmentCommandService {
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public DepartmentEntity create(DepartmentEntity department) {
		return departmentRepository.save(department);
	}

	@Override
	public void remove(Integer id) {
		departmentRepository.deleteById(id);
	}

}
