package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.mutant.challenge.api.datastore.dao.DatastoreDaoImpl;
import com.mutant.challenge.api.datastore.dao.IDatastoreDao;
import com.mutant.challenge.api.datastore.entity.Sequence;
import com.mutant.challenge.api.datastore.repository.IDatastoreRepository;
import com.mutant.challenge.api.util.ConstantUtils;

@ExtendWith(MockitoExtension.class)
@Import({ IDatastoreDao.class })
class DatastoreDaoImplUnitTest {

	@Mock
	IDatastoreRepository datastoreRepository;

	@InjectMocks
	private IDatastoreDao datastoreDao = new DatastoreDaoImpl();

	@Test
	void datastoreDaoInitializedCorrectlyTest() {
		assertThat(datastoreDao).isNotNull();
	}

	@Test
	void existSequenceInStorageTest() {

		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
		final int mutant = ConstantUtils.MUTANT;

		final Sequence mySequence = new Sequence(idSequence, mutant);
		Optional<Sequence> optSequence = Optional.ofNullable(mySequence);

		Mockito.when(datastoreRepository.findById(idSequence)).thenReturn(optSequence);

		final Sequence sequenceResponse = datastoreDao.existSequenceInStorage(idSequence);

		assertThat(sequenceResponse).isNotNull().hasFieldOrPropertyWithValue("idSequence", idSequence)
				.hasFieldOrPropertyWithValue("mutant", mutant);

	}

	@Test
	void existSequenceInStorageIsNullTest() {

		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";

		final Sequence mySequence = new Sequence();
		Optional<Sequence> optSequence = Optional.ofNullable(mySequence);
		Mockito.when(datastoreRepository.findById(idSequence)).thenReturn(optSequence);

		final Sequence sequenceResponse = datastoreDao.existSequenceInStorage(idSequence);

		assertThat(sequenceResponse.getIdSequence()).isNull();

	}

	@Test
	void notExistSequenceInStorageTest() {

		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTGGTCATG";

		Mockito.when(datastoreRepository.findById(idSequence)).thenReturn(null);

		assertThatNullPointerException().isThrownBy(() -> datastoreDao.existSequenceInStorage(idSequence));

	}

	@Test
	void saveSequenceInStorageTest() {

		final String idSequence = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
		final int mutant = ConstantUtils.MUTANT;

		final Sequence mySequence = new Sequence(idSequence, mutant);

		Mockito.when(datastoreRepository.save(Mockito.any())).thenReturn(mySequence);

		final Sequence otherSequence = datastoreDao.saveSequenceInStorage(idSequence, mutant);

		assertEquals(otherSequence.getIdSequence(), mySequence.getIdSequence());
	}
}
