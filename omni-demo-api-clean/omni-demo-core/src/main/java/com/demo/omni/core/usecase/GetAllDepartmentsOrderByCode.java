package com.demo.omni.core.usecase;

import java.util.List;

import com.demo.omni.core.usecase.dto.ListDepartmentDto;

public interface GetAllDepartmentsOrderByCode {
	List<ListDepartmentDto> execute();
}
