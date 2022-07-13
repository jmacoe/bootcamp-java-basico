package com.bootcamp.java.withdrawal.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootcamp.java.withdrawal.service.IRequestService;
import com.bootcamp.java.withdrawal.web.model.RequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(RequestController.class)
public class RequestControllerTest {

	@MockBean
	private IRequestService requestService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAll() throws Exception {
		mockMvc.perform(get("/v1/Request").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void getById() throws Exception {
		mockMvc.perform(get("/v1/Request/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		RequestModel request = RequestModel
				.builder()
				.id(Long.valueOf(1))
				.amount(Double.valueOf(100.0))
				.bankAccountNumber("123123-123123-123-123-2-312")
				.retirementDate(Timestamp.valueOf(LocalDateTime.of(2022,10,11,5,14)))
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/Request").content(asJsonString(request))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void update() throws Exception {
		RequestModel request = RequestModel
				.builder()
				.id(Long.valueOf(1))
				.amount(Double.valueOf(100.0))
				.bankAccountNumber("123123-123123-123-123-2-312")
				.retirementDate(Timestamp.valueOf(LocalDateTime.of(2022,10,11,5,14)))
				.build();

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/Request/1").content(asJsonString(request))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void deleteById() throws Exception {
		mockMvc.perform(delete("/v1/Request/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);
	}

}
