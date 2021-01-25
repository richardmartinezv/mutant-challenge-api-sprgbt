package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.mutant.challenge.api.algorithm.detection.IMutantDetection;
import com.mutant.challenge.api.algorithm.detection.MutantDetectionImpl;
import com.mutant.challenge.api.config.properties.MutantChallengeProperties;
import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;

@ExtendWith(MockitoExtension.class)
@Import({ IMutantDetection.class })
class MutantDetectionImplUnitTest {

	@Mock
	MutantChallengeProperties bussinessProperties;

	@InjectMocks
	private IMutantDetection algorithmService = new MutantDetectionImpl();

	@Test
	void algorithmServiceInitializedCorrectlyTest() {
		assertThat(algorithmService).isNotNull();
	}
	
	@Test
	void mutantDetectionAlgorithmArraySizeNotAllowedExceptionTest() {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };

		assertThatThrownBy(() -> algorithmService.mutantDetectionAlgorithm(dna))
				.isInstanceOf(ArraySizeNotAllowedException.class);
	}

	@Test
	void mutantDetectionAlgorithmIsMutantTest() throws ArraySizeNotAllowedException {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		Mockito.when(bussinessProperties.getMinimumNumberSequencesFind()).thenReturn(2);
		Mockito.when(bussinessProperties.getLengthLetterSequence()).thenReturn(4);

		boolean isMutant = algorithmService.mutantDetectionAlgorithm(dna);
		assertTrue(isMutant);
	}

	@Test
	void mutantDetectionAlgorithmIsHumanTest() throws ArraySizeNotAllowedException {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGATGG", "CCCGTA", "TCACTG" };

		Mockito.when(bussinessProperties.getMinimumNumberSequencesFind()).thenReturn(2);
		Mockito.when(bussinessProperties.getLengthLetterSequence()).thenReturn(4);

		boolean isMutant = algorithmService.mutantDetectionAlgorithm(dna);
		assertFalse(isMutant);
	}

}
