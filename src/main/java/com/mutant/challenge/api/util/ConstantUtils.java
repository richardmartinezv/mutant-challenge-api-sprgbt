package com.mutant.challenge.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantUtils {

	public static final String DECIMAL_FORMAT = "#.##";

	public static final int HUMAN = 0;
	public static final int MUTANT = 1;

	/*
	 * It corresponds to the maximum size of the quadratic matrix that could be
	 * stored in the datastorage
	 */
	public static final int MAXIMUM_NUMBER_CHARACTERS = 484;

	public static final String LOG_EXCEPTION = "EXCEPTION: ";
	public static final String LOG_REQUEST = "Request: [ {} ]";
	public static final String LOG_REQUEST_PARAMS = "Request: [ ContextPath: {} - HeaderNames: {} - ParameterMap: {} ]";
	public static final String LOG_RESP_MUTANT_CHALLENGE_API_EXCEPTION = "MUTANT_CHALLENGE_API_EXCEPTION: [ code: {} - message: {} - details: {} - date: {} ]";

	public static final String LETTER_SEQUENCE_NOT_ALLOWED_EXCEPTION = "Array of strings that represents the DNA sequence includes letters not allowed";
	public static final String ARRAY_SIZE_NOT_ALLOWED_EXCEPTION = "Array size is not corresponding to build a table of NxN";

}
