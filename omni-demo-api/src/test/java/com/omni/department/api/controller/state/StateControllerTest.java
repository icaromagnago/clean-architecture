package com.omni.department.api.controller.state;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.omni.department.api.domain.state.ListStatesOrderByNameUseCase;
import com.omni.department.api.domain.state.StateEntity;

@WebMvcTest(StateController.class)
class StateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ListStatesOrderByNameUseCase ListStatesOrderByNameService;
	
	@Test
	void whenGetListAllStates_thenReturnOk() throws Exception {

		StateEntity state1 = new StateEntity(1, "Rio de Janeiro");
		StateEntity state2 = new StateEntity(2, "Bahia");
		
		when(ListStatesOrderByNameService.execute()).thenReturn(List.of(state1, state2));
		
		mockMvc
			.perform(get("/states")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.status", is("OK")))
			.andExpect(jsonPath("$.messages", hasSize(1)))
			.andExpect(jsonPath("$.messages[0]", containsString("Process executed successfully")))
			.andExpect(jsonPath("$.result", hasSize(2)))
			.andExpect(jsonPath("$.result[*].id", containsInAnyOrder(1, 2)))
			.andExpect(jsonPath("$.result[*].name", containsInAnyOrder("Bahia", "Rio de Janeiro")));
	}

}
