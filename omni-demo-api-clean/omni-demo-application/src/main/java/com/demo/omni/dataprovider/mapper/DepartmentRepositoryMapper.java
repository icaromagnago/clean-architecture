package com.demo.omni.dataprovider.mapper;

import org.springframework.beans.BeanUtils;

import com.demo.omni.core.entity.Department;
import com.demo.omni.dataprovider.entity.DepartmentEntity;

public class DepartmentRepositoryMapper {
	
	public static DepartmentEntity toDatabaseEntity(final Department department) {
		var departmentEntity = new DepartmentEntity();
		BeanUtils.copyProperties(department, departmentEntity);
		
		return departmentEntity;
	}
	
	public static Department toDomainEntity(final DepartmentEntity departmentEntity) {
		var department = new Department();
		BeanUtils.copyProperties(departmentEntity, department);
		
		return department;
	}

}
