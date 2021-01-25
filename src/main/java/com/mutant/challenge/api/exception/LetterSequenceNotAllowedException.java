package com.mutant.challenge.api.exception;

/**
 * It is used in the validation of the letters corresponding to the array of
 * strings that represents the DNA sequence.
 * 
 * The only letters allowed are (A,T,G,C)
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public class LetterSequenceNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public LetterSequenceNotAllowedException(String message) {
		super(message);
	}
}
