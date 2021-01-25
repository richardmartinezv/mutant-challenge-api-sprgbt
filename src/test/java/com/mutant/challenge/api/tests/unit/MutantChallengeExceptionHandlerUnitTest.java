package com.mutant.challenge.api.tests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;
import com.mutant.challenge.api.exception.handler.MutantChallengeExceptionHandler;
import com.mutant.challenge.api.util.ConstantUtils;

@ExtendWith(MockitoExtension.class)
@Import({ MutantChallengeExceptionHandlerUnitTest.class })
class MutantChallengeExceptionHandlerUnitTest {

	@InjectMocks
	private MutantChallengeExceptionHandler handler;

	@Test
	void handleBusinessLetterSequenceNotAllowedExceptionTest() {

		ResponseEntity<Object> responseEntity = handler.handleBusinessException(
				new LetterSequenceNotAllowedException(ConstantUtils.LETTER_SEQUENCE_NOT_ALLOWED_EXCEPTION));

		assertEquals(500, responseEntity.getStatusCodeValue());

	}

	@Test
	void handleBusinessArraySizeNotAllowedExceptionTest() {

		ResponseEntity<Object> responseEntity = handler.handleBusinessException(
				new ArraySizeNotAllowedException(ConstantUtils.ARRAY_SIZE_NOT_ALLOWED_EXCEPTION));

		assertEquals(500, responseEntity.getStatusCodeValue());

	}

}
