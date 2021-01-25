package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.google.protobuf.TextFormat.ParseException;
import com.mutant.challenge.api.model.request.DnaRequest;
import com.mutant.challenge.api.model.response.ErrorResponse;

@JsonTest
class ErrorResponseUnitTest {

	@Autowired
	private JacksonTester<ErrorResponse> json;

	private static final String JSON_TO_DESERIALIZE = "{\"errorMessage\": \"Error has ocurred\"}";

	private static final String ERROR_MSG = "Error has ocurred";

	private ErrorResponse errorResponse;

	@BeforeEach
	public void setup() throws ParseException {
		errorResponse = ErrorResponse.builder().errorMessage(ERROR_MSG).build();
	}
	
	@Test
	void errorResponseInitializedCorrectlyTest() {
		assertThat(errorResponse).isNotNull();
	}
	
	@Test
	void fieldsSerializes() throws IOException {
		assertThat(this.json.write(errorResponse)).extractingJsonPathStringValue("@.errorMessage").isEqualTo(ERROR_MSG);

	}

	@Test
	void errorResponseSerializes() throws IOException {
		assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getErrorMessage()).isEqualTo(ERROR_MSG);
	}

	@Test
	void errorResponseToStringTest() {
		assertThat(errorResponse.toString()).isNotNull();
	}

	@Test
	void errorResponseBuilderTest() {
		ErrorResponse errorResponseBuilder = ErrorResponse.builder().errorMessage(ERROR_MSG).build();
		assertThat(errorResponseBuilder.getErrorMessage()).isEqualTo(errorResponse.getErrorMessage());
	}
}
