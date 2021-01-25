package com.mutant.challenge.api.tests.unit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mutant.challenge.api.controller.HealthCheckController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthCheckController.class)
class HealthCheckControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void healtcheckTest() throws Exception {

		final String endPoint = "/healtcheck";

		this.mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk());

	}

}
