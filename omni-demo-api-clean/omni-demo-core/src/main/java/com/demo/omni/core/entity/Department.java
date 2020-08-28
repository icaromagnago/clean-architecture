package com.demo.omni.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	
	private Integer id;
	
	private Integer code;
	
	private String name;
	
	private String local;

	private String city;
	
	private Board board;
	
	private State state;
}
