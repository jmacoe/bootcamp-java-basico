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

import com.bootcamp.java.withdrawal.service.IBalanceService;
import com.bootcamp.java.withdrawal.web.model.BalanceModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {

	@MockBean
	private IBalanceService balanceService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAll() throws Exception {
		mockMvc.perform(get("/v1/Balance").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void getById() throws Exception {
		mockMvc.perform(get("/v1/Balance/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		BalanceModel balance = BalanceModel
				.builder()
				.amountAvailable(Double.valueOf(100.0))
				.bankAccountNumber("1123-1123-123-123-2-312")
				.retirementDate(Timestamp.valueOf(LocalDateTime.of(2022,10,11,5,14)))
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/Balance").content(asJsonString(balance))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void update() throws Exception {
		BalanceModel balance = BalanceModel
				.builder()
				.amountAvailable(Double.valueOf(100.0))
				.bankAccountNumber("1223-1123-123-123-2-312")
				.retirementDate(Timestamp.valueOf(LocalDateTime.of(2022,10,11,5,14)))
				.build();
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/Balance/1").content(asJsonString(balance))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void deleteById() throws Exception {
		mockMvc.perform(delete("/v1/Balance/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);
	}

}
