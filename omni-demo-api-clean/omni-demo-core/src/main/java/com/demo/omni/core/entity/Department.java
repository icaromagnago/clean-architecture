package com.demo.omni.core.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {
	
	private Integer id;
	
	private Integer code;
	
	private String name;
	
	private String local;

	private String city;
	
	private Board board;
	
	private State state;
}
