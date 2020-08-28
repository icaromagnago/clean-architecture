package com.demo.omni.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.omni.core.usecase.impl.GetAllStatesImpl;
import com.demo.omni.dataprovider.impl.StateRepositoryImpl;

@Configuration
public class StateConfiguration {
	
	@Autowired
	private StateRepositoryImpl stateRepositoryInpl;
	
	@Bean
	public GetAllStatesImpl createGetAllStatesUseCase() {
		return new GetAllStatesImpl(stateRepositoryInpl);
	}

}
