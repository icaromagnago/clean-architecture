package com.demo.omni.core.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State {
	
	private Integer id;
	
	private String name;
	
	private String uf;
}
