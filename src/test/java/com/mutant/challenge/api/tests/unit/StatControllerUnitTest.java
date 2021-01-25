package com.mutant.challenge.api.tests.unit;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.mutant.challenge.api.controller.v1.StatController;
import com.mutant.challenge.api.model.response.StatsResponse;
import com.mutant.challenge.api.service.IStatService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatController.class)
class StatControllerUnitTest {

	@MockBean
	private IStatService statService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void statsTest() throws Exception {

		final long countMutantDna = 1;
		final long countHumanDna = 3;
		double ratio = 0.3;

		StatsResponse statsResponse = StatsResponse.builder().countMutantDna(countMutantDna)
				.countHumanDna(countHumanDna).ratio(ratio).build();

		Mockito.when(statService.getVerificationStatistic()).thenReturn(statsResponse);

		final String endPoint = "/v1/stats";

		this.mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.countMutantDna", is(countMutantDna), Long.class))
				.andExpect(jsonPath("$.countHumanDna", is(countHumanDna), Long.class))
				.andExpect(jsonPath("$.ratio", is(ratio), Double.class));
	}

	@Test
	void statsCountMutantDnaZeroTest() throws Exception {

		final long countMutantDna = 0;
		final long countHumanDna = 3;
		final double ratio = 0.0;

		StatsResponse statsResponse = StatsResponse.builder().countMutantDna(countMutantDna)
				.countHumanDna(countHumanDna).ratio(ratio).build();

		Mockito.when(statService.getVerificationStatistic()).thenReturn(statsResponse);

		final String endPoint = "/v1/stats";

		this.mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.countMutantDna", is(countMutantDna), Long.class))
				.andExpect(jsonPath("$.countHumanDna", is(countHumanDna), Long.class))
				.andExpect(jsonPath("$.ratio", is(ratio), Double.class));
	}

	@Test
	void statsCountHumanDnaZeroTest() throws Exception {

		final long countMutantDna = 1;
		final long countHumanDna = 0;
		final double ratio = 0.0;

		StatsResponse statsResponse = StatsResponse.builder().countMutantDna(countMutantDna)
				.countHumanDna(countHumanDna).ratio(ratio).build();

		Mockito.when(statService.getVerificationStatistic()).thenReturn(statsResponse);

		final String endPoint = "/v1/stats";

		this.mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.countMutantDna", is(countMutantDna), Long.class))
				.andExpect(jsonPath("$.countHumanDna", is(countHumanDna), Long.class))
				.andExpect(jsonPath("$.ratio", is(ratio), Double.class));
	}

	@Test
	void statsCountMutantAndHumanDnaZeroTest() throws Exception {

		final long countMutantDna = 0;
		final long countHumanDna = 0;
		final double ratio = 0.0;

		StatsResponse statsResponse = StatsResponse.builder().countMutantDna(countMutantDna)
				.countHumanDna(countHumanDna).ratio(ratio).build();

		Mockito.when(statService.getVerificationStatistic()).thenReturn(statsResponse);

		final String endPoint = "/v1/stats";

		this.mockMvc.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.countMutantDna", is(countMutantDna), Long.class))
				.andExpect(jsonPath("$.countHumanDna", is(countHumanDna), Long.class))
				.andExpect(jsonPath("$.ratio", is(ratio), Double.class));
	}
}
