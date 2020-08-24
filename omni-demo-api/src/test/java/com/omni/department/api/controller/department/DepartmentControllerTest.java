package com.omni.department.api.controller.department;

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

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.omni.department.api.domain.department.Board;
import com.omni.department.api.domain.department.DepartmentEntity;
import com.omni.department.api.domain.department.usecase.CreateDepartmentUseCase;
import com.omni.department.api.domain.department.usecase.FindByIdUseCase;
import com.omni.department.api.domain.department.usecase.ListDepartmentsOrderByCodeUseCase;
import com.omni.department.api.domain.department.usecase.RemoveDepartmentUseCase;
import com.omni.department.api.domain.department.usecase.UpdateDepartmentUseCase;
import com.omni.department.api.domain.state.StateEntity;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ListDepartmentsOrderByCodeUseCase ListDepartmentsOrderByCodeService;
	
	@MockBean
	private FindByIdUseCase findByIdService;
	
	@MockBean
	private CreateDepartmentUseCase createDepartmentService;
	
	@MockBean
	private UpdateDepartmentUseCase updateDepartmentService;
	
	@MockBean
	private RemoveDepartmentUseCase removeDepartmentService;

	@Test
	void whenGetListAllDepartments_thenReturnOk() throws Exception {
		var department1 = new DepartmentEntity(1, 1, "Compliance", "Av. Dois", "Salvador", Board.BUSINESS, new StateEntity(1, "Bahia"));
		var department2 = new DepartmentEntity(2, 2, "Compras", "Av. Cinco", "Barueri", Board.BUSINESS, new StateEntity(5, "São Paulo"));
		
		when(ListDepartmentsOrderByCodeService.execute()).thenReturn(List.of(department1, department2));
		
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
		
		var department = new DepartmentEntity(1, 1, "Compliance", "Rua Meteoro", "Salvador", Board.BUSINESS, new StateEntity(1, "Bahia"));
		when(createDepartmentService.execute(Mockito.any())).thenReturn(department);
		
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
		when(findByIdService.execute(Mockito.anyInt()))
			.thenThrow(new EmptyResultDataAccessException("", 1));
		
		mockMvc.perform(get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("Not Found")));
	}
	
	@Test
	void whenFindByExistingId_thenReturnOk() throws Exception {
		var department = new DepartmentEntity(1, 1, "Compliance", "Rua Meteoro", "Salvador", Board.BUSINESS, new StateEntity(1, "Bahia"));
		
		when(findByIdService.execute(Mockito.anyInt()))
			.thenReturn(department);
		
		mockMvc.perform(get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("OK")))
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
	void whenUpdatingNonExistingId_thenReturnNotFound() throws Exception {
		var departmentDto = "{"
				+ "\"code\": 1," 
				+ "\"name\": \"Compliance\", "
				+ "\"local\": \"Rua Meteoro\", "
				+ "\"city\": \"Salvador\", "
				+ "\"board\": \"BUSINESS\","
				+ "\"state\": {\"id\": \"1\"}"
				+ "}";
		
		when(updateDepartmentService.execute(Mockito.anyInt(), Mockito.any()))
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
		
		var department = new DepartmentEntity(1, 1, "Compliance", "Rua Nove de Julho", "Salvador", Board.BUSINESS, new StateEntity(1, "Bahia"));
		
		when(updateDepartmentService.execute(Mockito.anyInt(), Mockito.any()))
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
		doThrow(new EmptyResultDataAccessException("", 1)).when(removeDepartmentService).execute(Mockito.anyInt());
	
		mockMvc.perform(delete("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("Not Found")));
	}
	
	@Test
	void whenDeletingExistingId_thenReturnOk() throws Exception {
		doNothing().when(removeDepartmentService).execute(Mockito.anyInt());
	
		mockMvc.perform(delete("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.status", is("OK")))
		.andExpect(jsonPath("$.messages", hasSize(1)))
		.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")));
	}
}
