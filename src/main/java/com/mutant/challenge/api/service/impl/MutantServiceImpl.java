package com.mutant.challenge.api.service.impl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mutant.challenge.api.algorithm.detection.IMutantDetection;
import com.mutant.challenge.api.config.properties.MutantChallengeProperties;
import com.mutant.challenge.api.datastore.dao.IDatastoreDao;
import com.mutant.challenge.api.datastore.entity.Sequence;
import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;
import com.mutant.challenge.api.redis.dao.StatisticRedisDao;
import com.mutant.challenge.api.service.IMutantService;
import com.mutant.challenge.api.util.ConstantUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class service implements the business logic to detect if a human is a
 * mutant based on the DNA sequence and also the storage of information in a
 * database
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class MutantServiceImpl implements IMutantService {

	@Autowired
	IMutantDetection algorithmService;

	@Autowired
	IDatastoreDao datastoreDao;

	@Autowired
	StatisticRedisDao statisticRedisDao;

	@Autowired
	MutantChallengeProperties bussinessProperties;

	/**
	 * implement behavior to detect if a human is a mutant based on the DNA sequence
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
	@Override
	public boolean isMutant(final String[] dna) throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		log.info("dna.length: {}", dna.length);

		boolean isMutant = false;

		String[] upDna = Arrays.asList(dna).stream().map(String::toUpperCase).toArray(String[]::new);

		final String idSequence = checkAllowedLettersInDNASequence(upDna);
		
		Sequence seq = datastoreDao.existSequenceInStorage(idSequence);

		if (StringUtils.isBlank(seq.getIdSequence())) {
			log.info("Sequence has not been stored...");

			isMutant = algorithmService.mutantDetectionAlgorithm(upDna);
			
			seq = datastoreDao.saveSequenceInStorage(idSequence, isMutant ? ConstantUtils.MUTANT : ConstantUtils.HUMAN);

			incrementStatistics(isMutant);

		} else {
			log.info("Sequence has already been stored...");

			isMutant = seq.getMutant() == ConstantUtils.MUTANT;
		}

		log.debug("Sequence: {}", seq);
		
		log.debug("isMutant: {}", isMutant);

		return isMutant;
	}

	/**************************************************************
	 * ****************** PRIVATE METHODS *************************
	 **************************************************************
	 */

	/**
	 * check allowed letters in DNA sequence
	 * 
	 * @param dna represents each row of a table of (NxN) with the DNA sequence
	 * 
	 * @return the DNA sequence concatenated in a string
	 * 
	 * @throws LetterSequenceNotAllowedException if the array of strings that
	 *                                           represents the DNA sequence
	 *                                           includes letters not allowed
	 */
	private String checkAllowedLettersInDNASequence(final String[] dna) throws LetterSequenceNotAllowedException {

		final String joinSequence = String.join("", dna);

		final String strSequence = joinSequence.length() > ConstantUtils.MAXIMUM_NUMBER_CHARACTERS
				? joinSequence.substring(0, ConstantUtils.MAXIMUM_NUMBER_CHARACTERS)
				: joinSequence;
		
		if (!strSequence.matches(bussinessProperties.getLettersAllowedRegex())) {
			throw new LetterSequenceNotAllowedException(ConstantUtils.LETTER_SEQUENCE_NOT_ALLOWED_EXCEPTION);
		}

		return strSequence;
	}

	/**
	 * increment the value of the statistic in relation to whether the DNA sequence
	 * was mutant (true) or human (false)
	 * 
	 * @param isMutant (true) if it checks that the received DNA sequence
	 *                 corresponds to a mutant, otherwise it returns isMutant
	 *                 (false)
	 */
	@Async
	private void incrementStatistics(final boolean isMutant) {

		if (isMutant) {
			log.debug("DNA sequence corresponds to that of a mutant");
			statisticRedisDao.incrementCountMutantDna();

		} else {
			log.debug("DNA sequence corresponds to that of a human");
			statisticRedisDao.incrementCountHumanDna();
		}
	}

}
