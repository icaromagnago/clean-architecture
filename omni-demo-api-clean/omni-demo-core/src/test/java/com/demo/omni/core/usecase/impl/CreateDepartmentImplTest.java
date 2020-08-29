package com.demo.omni.core.usecase.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.entity.Board;
import com.demo.omni.core.entity.Department;
import com.demo.omni.core.entity.State;
import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.command.CreateDepartmentCommand;
import com.demo.omni.core.usecase.dto.StateIdDto;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;

public class CreateDepartmentImplTest {

	private DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
	
	private CreateDepartment createDepartmentUseCase = new CreateDepartmentImpl(departmentRepository);
	
	@Test(expected = DepartmentCodeAlreadyExistsException.class)
	public void throwDepartmentCodeAlreadyExistsException_whenAlreadyExistsCode() throws DepartmentCodeAlreadyExistsException {
		when(departmentRepository.doesDepartmentCodeExists(anyInt())).thenReturn(true);
		createDepartmentUseCase.execute(new CreateDepartmentCommand());
	}
	
	@Test
	public void createDepartmentSucess() throws DepartmentCodeAlreadyExistsException {
		when(departmentRepository.doesDepartmentCodeExists(anyInt())).thenReturn(false);
		var command = CreateDepartmentCommand.builder()
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(new StateIdDto(2))
				.build();
		
		var department = Department.builder()
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(State.builder().id(2).build())
				.build();
		
		when(departmentRepository.save(department)).thenReturn(department);
		
		createDepartmentUseCase.execute(command);
		
		verify(departmentRepository).save(department);
	}

}
