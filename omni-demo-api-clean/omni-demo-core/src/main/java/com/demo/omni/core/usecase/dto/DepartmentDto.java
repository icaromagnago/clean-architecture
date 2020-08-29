package com.demo.omni.core.usecase.dto;

import com.demo.omni.core.entity.Board;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DepartmentDto {
	
	private Integer id;
	
	private Integer code;
	
	private String name;

	private String local;

	private String city;

	private Board board;

	private StateIdDto state;
}
