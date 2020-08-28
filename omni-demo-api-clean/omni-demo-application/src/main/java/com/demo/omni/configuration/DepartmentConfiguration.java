package com.demo.omni.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.omni.core.usecase.impl.GetAllDepartmentsOrderByCodeImpl;
import com.demo.omni.dataprovider.impl.DepartmentRepositoryImpl;

@Configuration
public class DepartmentConfiguration {
	
	@Autowired
	private DepartmentRepositoryImpl departmentRepositoryImpl;
	
	@Bean
	public GetAllDepartmentsOrderByCodeImpl createGetAllDepartmentsUseCase() {
		return new GetAllDepartmentsOrderByCodeImpl(departmentRepositoryImpl);
		
	}
}
