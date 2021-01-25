package com.mutant.challenge.api.tests.unit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mutant.challenge.api.controller.v1.MutantController;
import com.mutant.challenge.api.model.request.DnaRequest;
import com.mutant.challenge.api.service.IMutantService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MutantController.class)
class MutantControllerUnitTest {

	@MockBean
	private IMutantService mutantService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void isMutantOkTest() throws Exception {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		DnaRequest requestBody = DnaRequest.builder().dna(dna).build();

		Mockito.when(mutantService.isMutant(requestBody.getDna())).thenReturn(true);

		final String endPoint = "/v1/mutant";

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(endPoint)
						.content("{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void isMutantForbbidenTest() throws Exception {

		final String[] dna = { "TTGCGA", "CAGTTC", "TTATGT", "AGAAGG", "CTCCTA", "TCACTG" };
		DnaRequest requestBody = DnaRequest.builder().dna(dna).build();

		Mockito.when(mutantService.isMutant(requestBody.getDna())).thenReturn(false);

		final String endPoint = "/v1/mutant";

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(endPoint)
						.content("{\"dna\": [\"TTGCGA\",\"CAGTTC\",\"TTATGT\",\"AGAAGG\",\"CTCCTA\",\"TCACTG\"]}")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isForbidden());
	}

}
