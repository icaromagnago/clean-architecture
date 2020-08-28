package com.demo.omni.core.usecase.dto.mapper;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.demo.omni.core.entity.Department;
import com.demo.omni.core.usecase.dto.ListDepartmentDto;

public class DepartmentMapper {
	
	public static List<ListDepartmentDto> toListDto(List<Department> departments) {
		return departments.stream()
				.map(department -> new ListDepartmentDto(
						department.getId(), department.getCode() , department.getName()))
				.collect(toList());
	}
}
