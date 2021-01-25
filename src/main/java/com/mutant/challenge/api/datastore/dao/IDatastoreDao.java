package com.mutant.challenge.api.datastore.dao;

import com.mutant.challenge.api.datastore.entity.Sequence;

/**
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

public interface IDatastoreDao {

	/**
	 * check if sequence has already been stored
	 * 
	 * @param idSequence represents each row of a table of (NxN) with the DNA
	 *                   sequence concatenated in a string
	 * @return The stored sequence
	 */
	Sequence existSequenceInStorage(final String idSequence);

	/**
	 * stores the concatenated DNA sequence in string
	 * 
	 * @param idSequence represents each row of a table of (NxN) with the DNA
	 *                   sequence concatenated in a string
	 * @param mutant   represents the detection result of the sequence in relation
	 *                   to whether it is mutant (true = 1) or human (false = 0)
	 * @return The stored sequence
	 */
	Sequence saveSequenceInStorage(final String idSequence, final int mutant);
}
