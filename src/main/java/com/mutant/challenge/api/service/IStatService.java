package com.mutant.challenge.api.service;

import com.mutant.challenge.api.model.response.StatsResponse;

/**
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public interface IStatService {

	/**
	 * defines behavior to get the statistics of the DNA sequence checks performed
	 * 
	 * @return statistics related to the information of the detected DNA sequences
	 */
	StatsResponse getVerificationStatistic();
}
