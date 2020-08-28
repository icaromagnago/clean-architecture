package com.demo.omni.core.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Board {
	EIS ("E.I.S."),
	RECOVERY("Recuperação"),
	BUSINESS("Negócios");
	
	private String description;
	
	public String getDescription() {
		return this.description;
	}
}
