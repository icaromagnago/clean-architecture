package com.demo.omni.dataprovider.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.entity.Department;
import com.demo.omni.dataprovider.mapper.DepartmentRepositoryMapper;
import com.demo.omni.dataprovider.repository.DepartmentJpaRepository;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
	
	private DepartmentJpaRepository departmentJpaRepository;
	
	public DepartmentRepositoryImpl(DepartmentJpaRepository departmentJpaRepository) {
		this.departmentJpaRepository = departmentJpaRepository;
	}

	@Override
	public Department save(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAllOrderByCode() {
		var departments = departmentJpaRepository.findAll(Sort.by(Order.asc("code")));
		
		return departments.stream()
				.map(DepartmentRepositoryMapper::toDomainEntity)
				.collect(toList());
	}

}
