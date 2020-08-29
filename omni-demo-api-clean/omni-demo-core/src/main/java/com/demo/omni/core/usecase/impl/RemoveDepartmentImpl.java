package com.demo.omni.core.usecase.impl;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.usecase.RemoveDepartment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoveDepartmentImpl implements RemoveDepartment {

	private final DepartmentRepository departmentRepository;
	
	@Override
	public void execute(Integer id) {
		departmentRepository.delete(id);
	}

}
