package com.omni.department.api.model;

public enum Board {
	EIS ("E.I.S."),
	RECOVERY("Recuperação"),
	BUSINESS("Negócios");
	
	
	private String description;
	
	private Board(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
