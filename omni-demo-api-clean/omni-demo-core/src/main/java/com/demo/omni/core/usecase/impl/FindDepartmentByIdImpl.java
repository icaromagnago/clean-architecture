package com.demo.omni.core.usecase.impl;

import java.util.Optional;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.usecase.FindDepartmentById;
import com.demo.omni.core.usecase.dto.FindDepartmentDto;
import com.demo.omni.core.usecase.dto.mapper.DepartmentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindDepartmentByIdImpl implements FindDepartmentById {
	
	private final DepartmentRepository departmentRepository;

	@Override
	public Optional<FindDepartmentDto> execute(Integer id) {
		return departmentRepository.findById(id)
				.map(DepartmentMapper::toFindDepartmentDto);
	}

}
