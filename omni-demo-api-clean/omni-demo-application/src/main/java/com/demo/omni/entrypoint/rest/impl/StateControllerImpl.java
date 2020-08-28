package com.demo.omni.entrypoint.rest.impl;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.omni.core.usecase.GetAllStates;
import com.demo.omni.entrypoint.rest.BaseResponseDto;
import com.demo.omni.entrypoint.rest.StateController;

@RestController
@RequestMapping("/states")
public class StateControllerImpl implements StateController {
	
	private GetAllStates getAllStatesUseCase;
	private MessageSource messageSource;
	
	public StateControllerImpl(GetAllStates getAllStatesUseCase, MessageSource messageSource) {
		this.getAllStatesUseCase = getAllStatesUseCase;
		this.messageSource = messageSource;
	}
	
	@GetMapping
	public ResponseEntity<BaseResponseDto> list() {
		var statesDto = getAllStatesUseCase.execute();
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), statesDto));
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}

}
