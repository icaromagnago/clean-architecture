package com.demo.omni.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ModelMapperConfiguration {
	
	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();
	}

}
