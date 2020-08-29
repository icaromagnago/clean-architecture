package com.demo.omni.core.usecase.dto.mapper;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.demo.omni.core.entity.Department;
import com.demo.omni.core.usecase.dto.CreatedDepartmentDto;
import com.demo.omni.core.usecase.dto.FindDepartmentDto;
import com.demo.omni.core.usecase.dto.ListDepartmentDto;
import com.demo.omni.core.usecase.dto.StateIdDto;

public class DepartmentMapper {
	
	public static List<ListDepartmentDto> toListDto(List<Department> departments) {
		return departments.stream()
				.map(department -> new ListDepartmentDto(
						department.getId(), department.getCode() , department.getName()))
				.collect(toList());
	}
	
	public static FindDepartmentDto toFindDepartmentDto(Department department) {
		return FindDepartmentDto.builder()
				.code(department.getCode())
				.name(department.getName())
				.local(department.getLocal())
				.city(department.getCity())
				.board(department.getBoard())
				.state(new StateIdDto(department.getState().getId()))
				.build();
	}
	
	public static CreatedDepartmentDto toCreatedDepartmentDto(Department department) {
		return CreatedDepartmentDto.builder()
			.id(department.getId())
			.code(department.getCode())
			.name(department.getName())
			.local(department.getLocal())
			.city(department.getCity())
			.board(department.getBoard())
			.state(new StateIdDto(department.getState().getId()))
			.build();
	}
}
