package com.mutant.challenge.api.tests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mutant.challenge.api.config.properties.MutantChallengeProperties;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = MutantChallengeProperties.class)
@TestPropertySource("classpath:bussiness-unit-test.properties")
class MutantChallengePropertiesTest {

	@Autowired
	private MutantChallengeProperties properties;

	@Test
	void lettersAllowedRegexTest() {

		assertNotNull(properties.getLettersAllowedRegex());
		assertEquals("^[ACGT]*$", properties.getLettersAllowedRegex());
	}

	@Test
	void minimumNumberSequencesFindTest() {

		assertEquals(2, properties.getMinimumNumberSequencesFind());
	}

	@Test
	void lengthLetterSequenceTest() {

		assertEquals(4, properties.getLengthLetterSequence());
	}
}
