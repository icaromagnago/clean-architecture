package com.demo.omni.core.usecase.exception;

public class DepartmentCodeAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DepartmentCodeAlreadyExistsException(String message) {
		super(message);
	}

}
