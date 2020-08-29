package com.demo.omni.dataprovider.mapper;

import com.demo.omni.core.entity.Department;
import com.demo.omni.dataprovider.entity.Board;
import com.demo.omni.dataprovider.entity.DepartmentEntity;

public class DepartmentRepositoryMapper {
	
	public static DepartmentEntity toDatabaseEntity(final Department department) {
		return DepartmentEntity.builder()
				.id(department.getId())
				.code(department.getCode())
				.city(department.getCity())
				.board(Board.valueOf(department.getBoard().name()))
				.local(department.getLocal())
				.name(department.getName())
				.state(StateRepositoryMapper.toDatabaseEntity(department.getState()))
				.build();
	}
	
	public static Department toDomainEntity(final DepartmentEntity departmentEntity) {
		
		return Department.builder()
				.id(departmentEntity.getId())
				.code(departmentEntity.getCode())
				.city(departmentEntity.getCity())
				.board(com.demo.omni.core.entity.Board.valueOf(departmentEntity.getBoard().name()))
				.local(departmentEntity.getLocal())
				.name(departmentEntity.getName())
				.state(StateRepositoryMapper.toDomainEntity(departmentEntity.getState()))
				.build();
	}

}
