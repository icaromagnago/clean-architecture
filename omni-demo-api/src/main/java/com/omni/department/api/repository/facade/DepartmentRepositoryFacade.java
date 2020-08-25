package com.omni.department.api.repository.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.repository.DepartmentRepository;

@Service
public class DepartmentRepositoryFacade {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public DepartmentEntity save(DepartmentEntity department) {
		return departmentRepository.save(department);
	}
	
	public void deleteById(Integer id) {
		departmentRepository.deleteById(id);
	}
	
	public List<DepartmentEntity> findAllOrderByCode() {
		return departmentRepository.findAll(Sort.by(Order.asc("code")));
	}

	public DepartmentEntity findById(Integer id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
}
