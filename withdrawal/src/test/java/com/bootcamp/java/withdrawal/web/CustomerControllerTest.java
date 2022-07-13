package com.bootcamp.java.withdrawal.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootcamp.java.withdrawal.service.ICustomerService;
import com.bootcamp.java.withdrawal.web.model.CustomerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@MockBean
	private ICustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAll() throws Exception {
		mockMvc.perform(get("/v1/Customer").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void getByDni() throws Exception {
		mockMvc.perform(get("/v1/Customer/dni/40853583").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getById() throws Exception {
		mockMvc.perform(get("/v1/Customer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		CustomerModel customer = CustomerModel
				.builder()
				.dni("12345678")
				.firstname("Max")
				.lastname("Planck")
				.afp("INTEGRA")
				.email("jmacoe@gmail.com")
				.phoneNumber("943133590")
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/Customer").content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void update() throws Exception {
		CustomerModel customer = CustomerModel
				.builder()
				.id(Long.valueOf(1))
				.dni("12345678")
				.firstname("Max")
				.lastname("Planck")
				.afp("PRIMA")
				.email("jmacoe@gmail.com")
				.phoneNumber("943133590")
				.build();

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/Customer/1").content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void deleteById() throws Exception {
		mockMvc.perform(delete("/v1/Customer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);
	}

}
