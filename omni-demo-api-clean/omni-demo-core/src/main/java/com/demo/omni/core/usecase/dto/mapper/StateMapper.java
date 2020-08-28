package com.demo.omni.core.usecase.dto.mapper;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.demo.omni.core.entity.State;
import com.demo.omni.core.usecase.dto.StateDto;

public class StateMapper {
	
	public static List<StateDto> toDto(List<State> states) {
		return states.stream()
				.map(state -> new StateDto(state.getId(), state.getName()))
				.collect(toList());
	}
}
