package com.demo.omni.core.usecase;

import java.util.Optional;

import com.demo.omni.core.usecase.dto.FindDepartmentDto;

public interface FindDepartmentById {
	
	Optional<FindDepartmentDto> execute(Integer id);
}
