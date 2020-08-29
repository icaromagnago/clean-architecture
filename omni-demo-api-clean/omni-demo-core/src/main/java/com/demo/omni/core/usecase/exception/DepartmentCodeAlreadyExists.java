package com.demo.omni.core.usecase.exception;

public class DepartmentCodeAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DepartmentCodeAlreadyExists(String message) {
		super(message);
	}

}
