package com.demo.omni.core.usecase.impl;

import java.util.List;

import com.demo.omni.core.dataprovider.StateRepository;
import com.demo.omni.core.usecase.GetAllStates;
import com.demo.omni.core.usecase.dto.StateDto;
import com.demo.omni.core.usecase.dto.mapper.StateMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllStatesImpl implements GetAllStates {
	
	private final StateRepository stateRepositoryService;

	@Override
	public List<StateDto> execute() {
		return StateMapper.toDto(stateRepositoryService.getAllStates());
	}

}
