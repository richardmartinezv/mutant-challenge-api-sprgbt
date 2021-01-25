package com.mutant.challenge.api.datastore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mutant.challenge.api.datastore.entity.Sequence;
import com.mutant.challenge.api.datastore.repository.IDatastoreRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This class service defines the calls to the datastorage repository layer
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@Service
public class DatastoreDaoImpl implements IDatastoreDao {

	@Autowired
	IDatastoreRepository datastoreRepository;

	/**
	 * check if sequence has already been stored
	 * 
	 * @param idSequence represents each row of a table of (NxN) with the DNA
	 *                   sequence concatenated in a string
	 * @return The stored sequence
	 */
	@Override
	public Sequence existSequenceInStorage(final String idSequence) {

		log.info("idSequence: {}", idSequence);

		return datastoreRepository.findById(idSequence).orElse(new Sequence());
	}

	/**
	 * stores the concatenated DNA sequence in string
	 * 
	 * @param idSequence represents each row of a table of (NxN) with the DNA
	 *                   sequence concatenated in a string
	 * @param mutant   represents the detection result of the sequence in relation
	 *                   to whether it is mutant (true = 1) or human (false = 0)
	 * @return The stored sequence
	 */
	@Async
	@Override
	public Sequence saveSequenceInStorage(final String idSequence, final int mutant) {

		log.info("idSequence: {}", idSequence);

		return datastoreRepository.save(Sequence.builder().idSequence(idSequence).mutant(mutant).build());
	}
}
