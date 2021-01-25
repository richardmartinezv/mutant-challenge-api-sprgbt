package com.mutant.challenge.api.algorithm.detection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutant.challenge.api.config.properties.MutantChallengeProperties;
import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.util.ConstantUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * The class component implements behavior for the detection algorithm if a
 * human is a mutant based on the DNA sequence
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@Component
public class MutantDetectionImpl implements IMutantDetection {

	@Autowired
	MutantChallengeProperties bussinessProperties;

	/*
	 * Arrays of positions on both the X and Y axis to search in all 3 possible
	 * positions from the current position.
	 */
	private static final int[] X = { 0, 1, 1 };
	private static final int[] Y = { 1, 0, 1 };

	/**
	 * implements behavior for the detection algorithm if a human is a mutant based
	 * on the DNA sequence
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * 
	 * @return isMutant true if it checks that the received DNA sequence corresponds
	 *         to a mutant, otherwise it returns isMutant false
	 *
	 * @throws ArraySizeNotAllowedException if array size is not corresponding to
	 *                                      build a table of NxN
	 */
	@Override
	public boolean mutantDetectionAlgorithm(final String[] dna) throws ArraySizeNotAllowedException {

		checkArraySize(dna);

		char[][] table = fillNxNTable(dna);

		return searchDnaSequences(table);
	}

	/**************************************************************
	 * ****************** PRIVATE METHODS *************************
	 **************************************************************
	 */

	/**
	 * check if Array size is corresponding to build a table of NxN
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * @throws ArraySizeNotAllowedException if array size is not corresponding to
	 *                                      build a table of NxN
	 */
	private void checkArraySize(final String[] dna) throws ArraySizeNotAllowedException {

		final int numRows = dna.length;
		final int numColumns = dna[0].length();

		if (numRows != numColumns) {
			log.warn("numberRows: {}  is different than the numberColumns: {}", numRows, numColumns);
			throw new ArraySizeNotAllowedException(ConstantUtils.ARRAY_SIZE_NOT_ALLOWED_EXCEPTION);
		}
	}

	/**
	 * create and fill the NxN matrix or table with the DNA sequences
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * @return table(matrix) of NxN with the DNA sequences
	 */
	private char[][] fillNxNTable(final String[] dna) {

		char[][] table = new char[dna.length][dna[0].length()];

		for (int i = 0; i < dna.length; i++) {

			for (int j = 0; j < dna[i].length(); j++) {
				table[i][j] = dna[i].charAt(j);
			}
		}

		return table;
	}

	/**
	 * search dna sequences
	 * 
	 * @param table represents the DNA sequence table of (NxN)
	 * 
	 * @return true if it checks that the received DNA sequence corresponds to a
	 *         mutant, otherwise it returns isMutant false
	 */
	private boolean searchDnaSequences(char[][] table) {

		log.info("minimum number of sequences to find: {}", bussinessProperties.getMinimumNumberSequencesFind());

		int numSequencesFound = 0;

		final int R = table.length;
		final int C = table[0].length;

		// Consider each point as a starting point and look up the given word
		for (int row = 0; row < R; row++) {

			for (int col = 0; col < C; col++) {

				if (findFourLetterSequence(table, row, col)) {
					numSequencesFound = numSequencesFound + 1;
					if (numSequencesFound == bussinessProperties.getMinimumNumberSequencesFind()) {
						log.info("number of sequences found: {}", numSequencesFound);
						return true;
					}

				}
			}
		}

		log.warn("number of sequences found: {}", numSequencesFound);
		return false;
	}

	/**
	 * searches in all the directions set from each of the points (row, column) in
	 * the table [row] [column]
	 * 
	 * @param table represents the DNA sequence table of (NxN)
	 * @param row   Position on the sequence table on the X axis
	 * @param col   Position on the sequence table on the Y axis
	 * @return true if it found a sequence of 4 equal letters otherwise it returns
	 *         false
	 */
	private boolean findFourLetterSequence(char[][] table, int row, int col) {

		final int R = table.length;
		final int C = table[0].length;

		char letter = table[row][col];

		// Search the word in the 3 directions starting from (row, column)
		for (int dir = 0; dir < 3; dir++) {

			// Initialize the starting point for the current address
			int k = 0;

			int rd = row + X[dir];
			int cd = col + Y[dir];

			// The first character is already marked, it matches the remaining characters
			for (k = 1; k < bussinessProperties.getLengthLetterSequence(); k++) {

				// If it's out of bounds then break
				if (rd >= R || rd < 0 || cd >= C || cd < 0)
					break;

				// If it doesn't match, break
				if (table[rd][cd] != letter)
					break;

				// Moving in a particular direction
				rd += X[dir];
				cd += Y[dir];
			}

			// If all the characters match, the value of must be equal to the length of the
			// word
			if (k == bussinessProperties.getLengthLetterSequence())
				return true;
		}

		return false;
	}

}
