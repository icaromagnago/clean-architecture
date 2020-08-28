package com.demo.omni.core.usecase.impl;

import java.util.List;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.usecase.GetAllDepartmentsOrderByCode;
import com.demo.omni.core.usecase.dto.ListDepartmentDto;
import com.demo.omni.core.usecase.dto.mapper.DepartmentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllDepartmentsOrderByCodeImpl implements GetAllDepartmentsOrderByCode {

	private final DepartmentRepository departmentRepository; 
	
	@Override
	public List<ListDepartmentDto> execute() {
		return DepartmentMapper.toListDto(departmentRepository.findAllOrderByCode());
	}
	

}
