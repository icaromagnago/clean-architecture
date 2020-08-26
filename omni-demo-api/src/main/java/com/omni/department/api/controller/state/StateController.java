package com.omni.department.api.controller.state;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omni.department.api.controller.BaseResponseDto;
import com.omni.department.api.controller.state.dto.StateQueryDto;
import com.omni.department.api.domain.state.ListStatesOrderByNameUseCase;

@RestController
@RequestMapping("/states")
public class StateController implements StateApi {
	
	@Autowired
	private ListStatesOrderByNameUseCase listStatesOrderByNameService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public ResponseEntity<BaseResponseDto> list() {
		var states = listStatesOrderByNameService.execute();
		
		List<StateQueryDto> statesDto = states.stream()
				.map(state -> modelMapper.map(state, StateQueryDto.class))
				.collect(toList());
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), statesDto));
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}

}
