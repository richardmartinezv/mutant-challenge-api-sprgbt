package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.google.protobuf.TextFormat.ParseException;
import com.mutant.challenge.api.datastore.entity.Sequence;

@JsonTest
class SequenceUnitTest {

	@Autowired
	private JacksonTester<Sequence> json;

	private static final String JSON_TO_DESERIALIZE = "{\"idSequence\": \"ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG\", \"mutant\": 1}";

	private static final String ID_SEQUENCE = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
	private static final int MUTANT = 1;

	private Sequence sequence;

	@BeforeEach
	public void setup() throws ParseException {
		sequence = Sequence.builder().idSequence(ID_SEQUENCE).mutant(MUTANT).build();
	}
	
	@Test
	void sequenceInitializedCorrectlyTest() {
		assertThat(sequence).isNotNull();
	}
	
	@Test
	void fieldsSerializes() throws IOException {
		assertThat(this.json.write(sequence)).extractingJsonPathStringValue("@.idSequence").isEqualTo(ID_SEQUENCE);
		assertThat(this.json.write(sequence)).extractingJsonPathNumberValue("@.mutant").isEqualTo(MUTANT);
	}

	@Test
	void sequenceResponseSerializes() throws IOException {
		assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getIdSequence()).isEqualTo(ID_SEQUENCE);
		assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getMutant()).isEqualTo(MUTANT);
	}

}
