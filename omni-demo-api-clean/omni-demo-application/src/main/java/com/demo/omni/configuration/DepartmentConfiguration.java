package com.demo.omni.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.omni.core.usecase.impl.CreateDepartmentImpl;
import com.demo.omni.core.usecase.impl.FindDepartmentByIdImpl;
import com.demo.omni.core.usecase.impl.GetAllDepartmentsOrderByCodeImpl;
import com.demo.omni.core.usecase.impl.RemoveDepartmentImpl;
import com.demo.omni.core.usecase.impl.UpdateDepartmentImpl;
import com.demo.omni.dataprovider.impl.DepartmentRepositoryImpl;

@Configuration
public class DepartmentConfiguration {
	
	@Autowired
	private DepartmentRepositoryImpl departmentRepositoryImpl;
	
	@Bean
	public GetAllDepartmentsOrderByCodeImpl createGetAllDepartmentsUseCase() {
		return new GetAllDepartmentsOrderByCodeImpl(departmentRepositoryImpl);
	}
	
	@Bean
	public FindDepartmentByIdImpl createFindDepartmentByIdUseCase() {
		return new FindDepartmentByIdImpl(departmentRepositoryImpl);
	}
	
	@Bean
	public RemoveDepartmentImpl createDeleteDepartmentUseCase() {
		return new RemoveDepartmentImpl(departmentRepositoryImpl);
	}
	
	@Bean
	public CreateDepartmentImpl createCreateDepartmentUseCase() {
		return new CreateDepartmentImpl(departmentRepositoryImpl);
	}
	
	@Bean
	public UpdateDepartmentImpl createUpdateDepartmentUseCase() {
		return new UpdateDepartmentImpl(departmentRepositoryImpl);
	}
}
