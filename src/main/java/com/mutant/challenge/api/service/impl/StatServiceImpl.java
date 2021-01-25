package com.mutant.challenge.api.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutant.challenge.api.model.response.StatsResponse;
import com.mutant.challenge.api.redis.dao.StatisticRedisDao;
import com.mutant.challenge.api.service.IStatService;
import com.mutant.challenge.api.util.ConstantUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class service the business logic to get the statistics of the DNA
 * sequence checks performed
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class StatServiceImpl implements IStatService {

	@Autowired
	StatisticRedisDao statisticRedisDao;

	/**
	 * implements behavior to get the statistics of the DNA sequence checks
	 * performed
	 * 
	 * @return statistics related to the information of the detected DNA sequences
	 */
	@Override
	public StatsResponse getVerificationStatistic() {
		
		double ratio = 0.0;
		
		final long countMutantDna = statisticRedisDao.getCountMutantDna();
		final long countHumanDna = statisticRedisDao.getCountHumanDna();

		if (countMutantDna > 0 && countHumanDna > 0) {
			ratio = calculateRatio(countMutantDna, countHumanDna);
		}

		StatsResponse statsResponse = StatsResponse.builder().countMutantDna(countMutantDna)
				.countHumanDna(countHumanDna).ratio(ratio).build();

		log.info("Stats: {}", statsResponse.toString());

		return statsResponse;
	}

	/**************************************************************
	 * ****************** PRIVATE METHODS *************************
	 **************************************************************
	 */

	/**
	 * Calculates the ratio between the number of DNA sequences detected as mutant
	 * and human
	 * 
	 * @param countMutantDna number of DNA sequences detected as mutant
	 * @param countHumanDna  number of DNA sequences detected as human
	 * 
	 * @return ratio between the number of DNA sequences detected as mutant and
	 *         human
	 */
	private double calculateRatio(final long countMutantDna, final long countHumanDna) {

		log.info("[ countMutantDna / countHumanDna ] : {} / {}", countMutantDna, countHumanDna);
		
		final double dRatio = countMutantDna / (double) countHumanDna;

		final DecimalFormat df = new DecimalFormat(ConstantUtils.DECIMAL_FORMAT);
		
		final double ratio = Double.parseDouble(df.format(dRatio).replace(",", "."));

		log.info("ratio: {}", ratio);

		return ratio;
	}
}
