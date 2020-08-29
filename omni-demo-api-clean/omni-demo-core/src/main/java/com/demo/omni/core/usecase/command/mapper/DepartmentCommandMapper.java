package com.demo.omni.core.usecase.command.mapper;

import com.demo.omni.core.entity.Department;
import com.demo.omni.core.entity.State;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;

public class DepartmentCommandMapper {
	
	public static Department toDepartment(CreateDepartmentCommand command) {
		return Department.builder()
				.code(command.getCode())
				.name(command.getName())
				.local(command.getLocal())
				.city(command.getCity())
				.board(command.getBoard())
				.state(State.builder().id(command.getState().getId()).build())
				.build();
				
	}
}
