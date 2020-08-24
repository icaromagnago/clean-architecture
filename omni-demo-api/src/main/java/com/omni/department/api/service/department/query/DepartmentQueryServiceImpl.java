package com.omni.department.api.service.department.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.omni.department.api.model.DepartmentEntity;
import com.omni.department.api.repository.DepartmentRepository;

@Service
public class DepartmentQueryServiceImpl implements DepartmentQueryService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentEntity> findAll() {
		return departmentRepository.findAll(Sort.by(Order.asc("code")));
	}

	@Override
	public DepartmentEntity findById(Integer id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(String .format("no department with id %s found", id), 1));
	}
}
