package com.demo.omni.dataprovider.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

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
		var departmentEntity = departmentJpaRepository
				.save(DepartmentRepositoryMapper.toDatabaseEntity(department));

		return DepartmentRepositoryMapper.toDomainEntity(departmentEntity);
	}

	@Override
	public void delete(Integer id) {
		departmentJpaRepository.deleteById(id);
	}

	@Override
	public Optional<Department> findById(Integer id) {
		return departmentJpaRepository.findById(id)
				.map(DepartmentRepositoryMapper::toDomainEntity);
	}

	@Override
	public List<Department> findAllOrderByCode() {
		var departments = departmentJpaRepository.findAll(Sort.by(Order.asc("code")));
		
		return departments.stream()
				.map(DepartmentRepositoryMapper::toDomainEntity)
				.collect(toList());
	}

	@Override
	public Boolean doesDepartmentCodeExists(Integer code) {
		return departmentJpaRepository.findByCode(code).isPresent();
	}

	@Override
	public Optional<Department> findByCode(Integer code) {
		return departmentJpaRepository.findByCode(code)
				.map(DepartmentRepositoryMapper::toDomainEntity);
	}

}
