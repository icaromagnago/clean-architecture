package com.demo.omni.core.usecase.command;

import com.demo.omni.core.entity.Board;
import com.demo.omni.core.usecase.dto.StateIdDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDepartmentCommand {
	
	private Integer code;
	
	private String name;
	
	private String local;

	private String city;
	
	private Board board;
	
	private StateIdDto state;
}
