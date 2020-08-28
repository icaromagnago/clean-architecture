package com.demo.omni.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class State {
	
	private Integer id;
	
	private String name;
	
	private String uf;
	
	public State(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
