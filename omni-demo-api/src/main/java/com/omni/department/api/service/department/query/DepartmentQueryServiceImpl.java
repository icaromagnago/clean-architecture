package com.omni.department.api.service.department.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omni.department.api.model.DepartmentEntity;
import com.omni.department.api.repository.DepartmentRepository;

@Service
public class DepartmentQueryServiceImpl implements DepartmentQueryService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentEntity> findAll() {
		return departmentRepository.findAll();
	}
}
