package com.mutant.challenge.api.service;

import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;

/**
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public interface IMutantService {

	/**
	 * defines behavior to detect if a human is a mutant based on the DNA sequence
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * 
	 * @return isMutant true if it checks that the received DNA sequence corresponds
	 *         to a mutant, otherwise it returns isMutant false
	 *
	 * @throws ArraySizeNotAllowedException      if the size of the array does not
	 *                                           correspond to NxN
	 * @throws LetterSequenceNotAllowedException if the array of strings that
	 *                                           represents the DNA sequence
	 *                                           includes letters not allowed
	 */
	boolean isMutant(String[] dna) throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException;
}
