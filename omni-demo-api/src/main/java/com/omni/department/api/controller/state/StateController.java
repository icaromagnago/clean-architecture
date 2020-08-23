package com.omni.department.api.controller.state;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omni.department.api.controller.state.dto.StateQueryDto;
import com.omni.department.api.service.state.query.StateQueryService;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateQueryService stateQueryService;
	
	@GetMapping
	public List<StateQueryDto> list() {
		
		var states = stateQueryService.findAll();
		
		List<StateQueryDto> statesDto = states.stream()
				.map(state -> new StateQueryDto(state.getId(), state.getName()))
				.collect(toList());
		
		return statesDto;
	}

}
