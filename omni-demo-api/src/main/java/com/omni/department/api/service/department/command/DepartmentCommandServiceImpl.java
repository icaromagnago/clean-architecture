package com.omni.department.api.service.department.command;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	@Override
	public DepartmentEntity update(Integer id, DepartmentEntity department) {
		DepartmentEntity persistedDepartment = departmentRepository.findById(id).orElse(null);
		
		if (persistedDepartment == null) {
			throw new EmptyResultDataAccessException(String .format("no department with id %s found for update", id), 1);
		}
		
		BeanUtils.copyProperties(department, persistedDepartment, "id");
		
		return departmentRepository.save(persistedDepartment);
	}

}
