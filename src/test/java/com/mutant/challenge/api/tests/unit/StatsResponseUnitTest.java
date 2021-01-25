package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.google.protobuf.TextFormat.ParseException;
import com.mutant.challenge.api.model.response.StatsResponse;

@JsonTest
class StatsResponseUnitTest {

	@Autowired
	private JacksonTester<StatsResponse> json;

	private static final String JSON_TO_DESERIALIZE = "{\"countMutantDna\": 1,\"countHumanDna\": 3,\"ratio\": 0.3}";

	private static final long COUNT_MUTANT_DNA = 1L;
	private static final long COUNT_HUMAN_DNA = 3;
	private static final double RATIO = 0.3;

	private StatsResponse statsResponse;

	@BeforeEach
	public void setup() throws ParseException {
		statsResponse = StatsResponse.builder().countMutantDna(COUNT_MUTANT_DNA).countHumanDna(COUNT_HUMAN_DNA)
				.ratio(RATIO).build();
	}

	@Test
	void statsResponseInitializedCorrectlyTest() {
		assertThat(statsResponse).isNotNull();
	}

	@Test
	void fieldsSerializes() throws IOException {
		assertThat(this.json.write(statsResponse)).extractingJsonPathNumberValue("@.countMutantDna",
				is(COUNT_MUTANT_DNA), Long.class);
		assertThat(this.json.write(statsResponse)).extractingJsonPathNumberValue("@.countHumanDna",
				is(COUNT_MUTANT_DNA), Long.class);
		assertThat(this.json.write(statsResponse)).extractingJsonPathNumberValue("@.ratio", is(COUNT_MUTANT_DNA),
				Double.class);

	}

	@Test
	void statsResponseSerializes() throws IOException {
		assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getCountMutantDna()).isEqualTo(COUNT_MUTANT_DNA);
	}

	@Test
	void statsResponseToStringTest() {
		assertThat(statsResponse.toString()).isNotNull();
	}

	@Test
	void statsResponseBuilderTest() {
		StatsResponse statsResponseBuild = StatsResponse.builder().countMutantDna(COUNT_MUTANT_DNA)
				.countHumanDna(COUNT_HUMAN_DNA).ratio(RATIO).build();
		assertThat(statsResponseBuild.getCountMutantDna()).isEqualTo(statsResponse.getCountMutantDna());
		assertThat(statsResponseBuild.getCountHumanDna()).isEqualTo(statsResponse.getCountHumanDna());
		assertThat(statsResponseBuild.getRatio()).isEqualTo(statsResponse.getRatio());
	}
}
