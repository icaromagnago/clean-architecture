package com.omni.department.api.controller.department.dto;

import com.omni.department.api.model.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QueryDepartmentDto {
	
	private Integer id;
	
	private Integer code;
	
	private String name;

	private String local;

	private String city;

	private Board board;

	private StateDto state;
	
	@Getter
	@Setter
	@NoArgsConstructor
	private static class StateDto {

		private Integer id;
	}
}