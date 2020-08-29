package com.demo.omni.entrypoint.rest.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.omni.core.entity.Board;
import com.demo.omni.core.usecase.CreateDepartment;
import com.demo.omni.core.usecase.FindDepartmentById;
import com.demo.omni.core.usecase.GetAllDepartmentsOrderByCode;
import com.demo.omni.core.usecase.RemoveDepartment;
import com.demo.omni.core.usecase.UpdateDepartment;
import com.demo.omni.core.usecase.dto.DepartmentDto;
import com.demo.omni.core.usecase.dto.FindDepartmentDto;
import com.demo.omni.core.usecase.dto.ListDepartmentDto;
import com.demo.omni.core.usecase.dto.StateIdDto;

@WebMvcTest(DepartmentControllerImpl.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GetAllDepartmentsOrderByCode getAllDepartmentsUseCase;
	
	@MockBean
	private FindDepartmentById findDepartmentByIdUseCase;
	
	@MockBean
	private RemoveDepartment removeDepartmentUseCase;
	
	@MockBean
	private CreateDepartment createDepartmentUseCase;
	
	@MockBean
	private UpdateDepartment updateDepartmentUseCase;
	
	@MockBean 
	private ModelMapper modelMapper;
	
	

	@Test
	void whenGetListAllDepartments_thenReturnOk() throws Exception {
		var department1 = new ListDepartmentDto(1, 1, "Compliance");
		var department2 = new ListDepartmentDto(2, 2, "Compras");
		
		when(getAllDepartmentsUseCase.execute()).thenReturn(List.of(department1, department2));
		
		mockMvc.perform(get("/departments").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.status", is("OK")))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")))
			.andExpect(jsonPath("$.result", hasSize(2)))
			.andExpect(jsonPath("$.result[*].id", containsInAnyOrder(1, 2)))
			.andExpect(jsonPath("$.result[*].code", containsInAnyOrder(1, 2)))
			.andExpect(jsonPath("$.result[*].name", containsInAnyOrder("Compliance", "Compras")));
	}
	
	@Test
	void whenPostNullCode_thenReturnsBadResquest400() throws Exception {
		var departmentMissingCodeDto = "{"
				+ "\"name\": \"Compliance\", "
				+ "\"local\": \"Rua Meteoro\", "
				+ "\"city\": \"Salvador\", "
				+ "\"board\": \"BUSINESS\","
				+ "\"state\": {\"id\": \"1\"}"
				+ "}";
		
		mockMvc
			.perform(post("/departments")
					.contentType(MediaType.APPLICATION_JSON)
					.content(departmentMissingCodeDto))
			.andExpect(status().isBadRequest())
			.andDo(print())
			.andExpect(jsonPath("$.status", is("Bad Request")))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("code: code may not be null")));
	}
	
	@Test
	void whenPost_thenReturnsOk() throws Exception {
		var departmentDto = "{"
				+ "\"code\": 1," 
				+ "\"name\": \"Compliance\", "
				+ "\"local\": \"Rua Meteoro\", "
				+ "\"city\": \"Salvador\", "
				+ "\"board\": \"BUSINESS\","
				+ "\"state\": {\"id\": \"1\"}"
				+ "}";
		
		var department = new DepartmentDto(1, 1, "Compliance", "Rua Meteoro", "Salvador", Board.BUSINESS, new StateIdDto(1));
		when(createDepartmentUseCase.execute(Mockito.any())).thenReturn(department);
		
		mockMvc
			.perform(post("/departments")
					.contentType(MediaType.APPLICATION_JSON)
					.content(departmentDto))
			.andExpect(status().isCreated())
			.andDo(print())
			.andExpect(jsonPath("$.status", is("Created")))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")))
			.andExpect(jsonPath("$.result.id", is(1)))
			.andExpect(jsonPath("$.result.code", is(1)))
			.andExpect(jsonPath("$.result.name", is("Compliance")))
			.andExpect(jsonPath("$.result.local", is("Rua Meteoro")))
			.andExpect(jsonPath("$.result.city", is("Salvador")))
			.andExpect(jsonPath("$.result.board", is("BUSINESS")))
			.andExpect(jsonPath("$.result.state.id", is(1)));
		
	}
	
	@Test
	void whenFindByNonExistingId_thenReturnNotFound() throws Exception {
		when(findDepartmentByIdUseCase.execute(Mockito.anyInt()))
			.thenThrow(new EmptyResultDataAccessException("", 1));
		
		mockMvc.perform(get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("Not Found")));
	}
	
	@Test
	void whenFindByExistingId_thenReturnOk() throws Exception {
		var department = new FindDepartmentDto(1, "Compliance", "Rua Meteoro", "Salvador", Board.BUSINESS, new StateIdDto(1));
		
		when(findDepartmentByIdUseCase.execute(Mockito.anyInt()))
			.thenReturn(Optional.of(department));
		
		mockMvc.perform(get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("OK")))
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")))
		.andExpect(jsonPath("$.result.code", is(1)))
		.andExpect(jsonPath("$.result.name", is("Compliance")))
		.andExpect(jsonPath("$.result.local", is("Rua Meteoro")))
		.andExpect(jsonPath("$.result.city", is("Salvador")))
		.andExpect(jsonPath("$.result.board", is("BUSINESS")))
		.andExpect(jsonPath("$.result.state.id", is(1)));
	}
	
	@Test
	void whenUpdatingNonExistingId_thenReturnNotFound() throws Exception {
		var departmentDto = "{"
				+ "\"code\": 1," 
				+ "\"name\": \"Compliance\", "
				+ "\"local\": \"Rua Meteoro\", "
				+ "\"city\": \"Salvador\", "
				+ "\"board\": \"BUSINESS\","
				+ "\"state\": {\"id\": \"1\"}"
				+ "}";
		
		when(updateDepartmentUseCase.execute(Mockito.anyInt(), Mockito.any()))
			.thenThrow(new EmptyResultDataAccessException(1));
	
		mockMvc.perform(put("/departments/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(departmentDto))
		.andExpect(status().isNotFound())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("Not Found")));
	}
	
	@Test
	void whenUpdating_thenReturnOk() throws Exception {
		var departmentDtoIn = "{"
				+ "\"code\": 1," 
				+ "\"name\": \"Compliance\", "
				+ "\"local\": \"Rua Meteoro\", "
				+ "\"city\": \"Salvador\", "
				+ "\"board\": \"BUSINESS\","
				+ "\"state\": {\"id\": \"1\"}"
				+ "}";
		
		var department = new DepartmentDto(1, 1, "Compliance", "Rua Nove de Julho", "Salvador", Board.BUSINESS, new StateIdDto(1));
		
		when(updateDepartmentUseCase.execute(Mockito.anyInt(), Mockito.any()))
			.thenReturn(department);
	
		mockMvc.perform(put("/departments/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(departmentDtoIn))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("OK")))
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")))
		.andExpect(jsonPath("$.result.id", is(1)))
		.andExpect(jsonPath("$.result.code", is(1)))
		.andExpect(jsonPath("$.result.name", is("Compliance")))
		.andExpect(jsonPath("$.result.local", is("Rua Nove de Julho")))
		.andExpect(jsonPath("$.result.city", is("Salvador")))
		.andExpect(jsonPath("$.result.board", is("BUSINESS")))
		.andExpect(jsonPath("$.result.state.id", is(1)));
	}
	
	@Test
	void whenDeletingNonExistingId_thenReturnNotFound() throws Exception {
		doThrow(new EmptyResultDataAccessException("", 1)).when(removeDepartmentUseCase).execute(Mockito.anyInt());
	
		mockMvc.perform(delete("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("Not Found")));
	}
	
	@Test
	void whenDeletingExistingId_thenReturnOk() throws Exception {
		doNothing().when(removeDepartmentUseCase).execute(Mockito.anyInt());
	
		mockMvc.perform(delete("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("OK")))
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")));
	}
}
