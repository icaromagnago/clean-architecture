package com.demo.omni.core.usecase.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.demo.omni.core.dataprovider.DepartmentRepository;
import com.demo.omni.core.entity.Board;
import com.demo.omni.core.entity.Department;
import com.demo.omni.core.entity.State;
import com.demo.omni.core.usecase.UpdateDepartment;
import com.demo.omni.core.usecase.command.UpdateDepartmentCommand;
import com.demo.omni.core.usecase.dto.StateIdDto;
import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExistsException;
import com.demo.omni.core.usecase.exception.DepartmentNotFoundException;

public class UpdateDepartmentImplTest {
	
	private DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
	
	private UpdateDepartment updateDepartmentUseCase = new UpdateDepartmentImpl(departmentRepository);
	

	@Test(expected = DepartmentNotFoundException.class)
	public void throwDepartmentNotFoundException_whenFindById() throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		updateDepartmentUseCase.execute(1, new UpdateDepartmentCommand());
	}
	
	@Test(expected = DepartmentCodeAlreadyExistsException.class)
	public void throwDepartmentCodeAlreadyExistsException_whenAlreadyExistsCode() throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		
		var departmentFindById = Department.builder()
				.id(1)
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(State.builder().id(2).build())
				.build();
		
		var departmentFindByCode = Department.builder()
				.id(2)
				.board(Board.BUSINESS)
				.code(2)
				.city("Linhares")
				.local("Rua Comendador Rafael")
				.name("Logistica")
				.state(State.builder().id(5).build())
				.build();
		
		var updatingCommandDepartment = UpdateDepartmentCommand.builder()
				.board(Board.BUSINESS)
				.code(2)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(new StateIdDto(2))
				.build();
		
		when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(departmentFindById));
		when(departmentRepository.findByCode(anyInt())).thenReturn(Optional.of(departmentFindByCode));
		
		updateDepartmentUseCase.execute(1, updatingCommandDepartment);
	}
	
	@Test
	public void updateSucess_whenUpdateDepartmentWithItsSameCode() throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		var departmentFindById = Department.builder()
				.id(1)
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(State.builder().id(2).build())
				.build();
		
		var departmentFindByCode = Department.builder()
				.id(1)
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(State.builder().id(2).build())
				.build();
		
		var updatingCommandDepartment = UpdateDepartmentCommand.builder()
				.board(Board.BUSINESS)
				.code(1)
				.city("Rio de Janeiro")
				.local("Av. Atlantica")
				.name("Financeiro")
				.state(new StateIdDto(5))
				.build();
		
		var updatedDepartment = Department.builder()
				.board(Board.BUSINESS)
				.code(1)
				.city("Rio de Janeiro")
				.local("Av. Atlantica")
				.name("Financeiro")
				.state(State.builder().id(5).build())
				.build();
		
		when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(departmentFindById));
		when(departmentRepository.findByCode(anyInt())).thenReturn(Optional.of(departmentFindByCode));
		when(departmentRepository.save(any())).thenReturn(updatedDepartment);
		
		updateDepartmentUseCase.execute(1, updatingCommandDepartment);
		
		verify(departmentRepository).save(any());
	}
	
	@Test
	public void updateSucess_whenUpdateDepartmentWithAnotherAvailableCode() throws DepartmentNotFoundException, DepartmentCodeAlreadyExistsException {
		var departmentFindById = Department.builder()
				.id(1)
				.board(Board.BUSINESS)
				.code(1)
				.city("Salvador")
				.local("Av. Bonfim")
				.name("Financeiro")
				.state(State.builder().id(2).build())
				.build();
		
		var updatingCommandDepartment = UpdateDepartmentCommand.builder()
				.board(Board.BUSINESS)
				.code(2)
				.city("Rio de Janeiro")
				.local("Av. Atlantica")
				.name("Financeiro")
				.state(new StateIdDto(5))
				.build();
		
		var updatedDepartment = Department.builder()
				.board(Board.BUSINESS)
				.code(2)
				.city("Rio de Janeiro")
				.local("Av. Atlantica")
				.name("Financeiro")
				.state(State.builder().id(5).build())
				.build();
		
		when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(departmentFindById));
		when(departmentRepository.findByCode(anyInt())).thenReturn(Optional.empty());
		when(departmentRepository.save(any())).thenReturn(updatedDepartment);
		
		updateDepartmentUseCase.execute(1, updatingCommandDepartment);
		
		verify(departmentRepository).save(any());
	}

}
