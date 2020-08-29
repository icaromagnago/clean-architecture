package com.demo.omni.core.usecase.dto;

import com.demo.omni.core.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class FindDepartmentDto {
	
	private Integer code;
	
	private String name;

	private String local;

	private String city;

	private Board board;

	private StateIdDto state;
}
