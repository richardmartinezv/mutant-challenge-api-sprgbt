package com.mutant.challenge.api.exception;

/**
 * It is used in the validation of the array size is not corresponding to build
 * a table of NxN
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public class ArraySizeNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ArraySizeNotAllowedException(String message) {
		super(message);
	}
}
