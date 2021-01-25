package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.mutant.challenge.api.algorithm.detection.IMutantDetection;
import com.mutant.challenge.api.config.properties.MutantChallengeProperties;
import com.mutant.challenge.api.datastore.dao.IDatastoreDao;
import com.mutant.challenge.api.datastore.entity.Sequence;
import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;
import com.mutant.challenge.api.redis.dao.StatisticRedisDao;
import com.mutant.challenge.api.service.IMutantService;
import com.mutant.challenge.api.service.impl.MutantServiceImpl;
import com.mutant.challenge.api.util.ConstantUtils;

@ExtendWith(MockitoExtension.class)
@Import({ IMutantService.class })
class MutantServiceImplUnitTest {

	@Mock
	IDatastoreDao datastoreDao;

	@Mock
	StatisticRedisDao statisticRedisDao;

	@Mock
	IMutantDetection algorithmService;

	@Mock
	MutantChallengeProperties bussinessProperties;

	@InjectMocks
	private IMutantService mutantService = new MutantServiceImpl();

	@Test
	void mutantServiceInitializedCorrectlyTest() {
		assertThat(mutantService).isNotNull();
	}

	@Test
	void isMutantLetterSequenceNotAllowedExceptionTest() {

		final String[] dna = { "ATGCGA", "CAXTGC", "TTAYGT", "AGAAGG", "CCZCTA", "TCACTG" };

		Mockito.when(bussinessProperties.getLettersAllowedRegex()).thenReturn("^[ACGT]*$");

		assertThatThrownBy(() -> mutantService.isMutant(dna)).isInstanceOf(LetterSequenceNotAllowedException.class);
	}

	@Test
	void isMutantExistSequenceInStorageTest() throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
		final int mutant = ConstantUtils.MUTANT;
		final Sequence mySequence = Sequence.builder().idSequence(idSequence).mutant(mutant).build();

		Mockito.when(bussinessProperties.getLettersAllowedRegex()).thenReturn("^[ACGT]*$");
		Mockito.when(datastoreDao.existSequenceInStorage(idSequence)).thenReturn(mySequence);

		boolean isMutant = mutantService.isMutant(dna);

		assertTrue(isMutant);
	}

	@Test
	void isHumantExistSequenceInStorageTest() throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		final String[] dna = { "GTGCGA", "CAGTGA", "TTATGT", "AGAAGC", "CCCCTA", "TCACTG" };
		final String idSequence = "GTGCGACAGTGATTATGTAGAAGCCCCCTATCACTG";
		final int mutant = ConstantUtils.HUMAN;
		final Sequence mySequence = Sequence.builder().idSequence(idSequence).mutant(mutant).build();

		Mockito.when(bussinessProperties.getLettersAllowedRegex()).thenReturn("^[ACGT]*$");
		Mockito.when(datastoreDao.existSequenceInStorage(idSequence)).thenReturn(mySequence);

		boolean isMutant = mutantService.isMutant(dna);

		assertFalse(isMutant);
	}

	@Test
	void isMutantNotExistSequenceInStorageTest()
			throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";

		Mockito.when(bussinessProperties.getLettersAllowedRegex()).thenReturn("^[ACGT]*$");
		Mockito.when(datastoreDao.existSequenceInStorage(idSequence)).thenReturn(new Sequence());
		Mockito.when(algorithmService.mutantDetectionAlgorithm(dna)).thenReturn(true);

		boolean isMutant = mutantService.isMutant(dna);

		verify(datastoreDao).saveSequenceInStorage(idSequence, isMutant ? ConstantUtils.MUTANT : ConstantUtils.HUMAN);
		verify(statisticRedisDao).incrementCountMutantDna();
		assertTrue(isMutant);
	}

	@Test
	void isHumanNotExistSequenceInStorageTest() throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		final String[] dna = { "GTGCGA", "CAGTGA", "TTATGT", "AGAAGC", "CCCCTA", "TCACTG" };
		final String idSequence = "GTGCGACAGTGATTATGTAGAAGCCCCCTATCACTG";

		Mockito.when(bussinessProperties.getLettersAllowedRegex()).thenReturn("^[ACGT]*$");
		Mockito.when(datastoreDao.existSequenceInStorage(idSequence)).thenReturn(new Sequence());
		Mockito.when(algorithmService.mutantDetectionAlgorithm(dna)).thenReturn(false);

		boolean isMutant = mutantService.isMutant(dna);

		verify(datastoreDao).saveSequenceInStorage(idSequence, isMutant ? ConstantUtils.MUTANT : ConstantUtils.HUMAN);
		verify(statisticRedisDao).incrementCountHumanDna();
		assertFalse(isMutant);
	}

}
