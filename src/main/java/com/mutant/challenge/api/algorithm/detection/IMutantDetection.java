package com.mutant.challenge.api.algorithm.detection;

import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;

/**
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public interface IMutantDetection {

	/**
	 * defines behavior for the detection algorithm if a human is a mutant based on the DNA sequence
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * 
	 * @return isMutant true if it checks that the received DNA sequence corresponds
	 *         to a mutant, otherwise it returns isMutant false
	 *
	 * @throws ArraySizeNotAllowedException if the size of the array does not
	 *                                      correspond to NxN
	 */
	boolean mutantDetectionAlgorithm(String[] dna) throws ArraySizeNotAllowedException;

}
