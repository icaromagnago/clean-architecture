package com.demo.omni.core.usecase;

import java.util.List;

import com.demo.omni.core.usecase.dto.StateDto;

public interface GetAllStates {
	
	List<StateDto> execute();

}
