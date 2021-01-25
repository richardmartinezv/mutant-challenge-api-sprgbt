package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.google.protobuf.TextFormat.ParseException;
import com.mutant.challenge.api.model.request.DnaRequest;

@JsonTest
class DnaRequestUnitTest {

	@Autowired
	private JacksonTester<DnaRequest> json;

	private static final String JSON_TO_DESERIALIZE = "{\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";

	private static final String[] DNA_ARRAY = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

	private DnaRequest dnaRequest;

	@BeforeEach
	public void setup() throws ParseException {
		dnaRequest = DnaRequest.builder().dna(DNA_ARRAY).build();
	}

	@Test
	void dnaRequestInitializedCorrectlyTest() {
		assertThat(dnaRequest).isNotNull();
	}

	@Test
	void dnaSerializes() throws IOException {
		assertThat(this.json.write(dnaRequest)).extractingJsonPathArrayValue("@.dna")
				.isEqualTo(Arrays.asList(DNA_ARRAY));
	}

	@Test
	void dnaRequestSerializes() throws IOException {
		assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getDna()).isEqualTo(DNA_ARRAY);
	}

	@Test
	void dnaRequestToStringTest() {
		DnaRequest dnaRequestBuilder = DnaRequest.builder().dna(DNA_ARRAY).build();
		assertThat(dnaRequest.toString()).isNotNull();
		assertThat(dnaRequest.toString()).hasToString(dnaRequestBuilder.toString());
	}

	@Test
	void dnaRequestBuilderTest() {
		DnaRequest dnaRequestBuilder = DnaRequest.builder().dna(DNA_ARRAY).build();
		assertThat(dnaRequestBuilder.getDna()).isEqualTo(dnaRequest.getDna());
	}
}
