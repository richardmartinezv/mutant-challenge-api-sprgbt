package com.mutant.challenge.api.redis.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutant.challenge.api.config.properties.RedisProperties;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * This class component defines the operations to the redis dao layer
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@Component
public class StatisticRedisDao extends RedisDao {

	private static final String ERROR_REDIS = "A communication error with Redis has occurred: ";

	@Autowired
	RedisProperties redisProperties;

	/**
	 * {@inheritDoc}.
	 */
	@Override
	protected void initJedisPool() {

		this.getJedisPool(this.redisProperties.getMain());
	}

	/**
	 * query in a key in redis the number of sequences detected as mutant
	 * 
	 * @return number of DNA sequences detected as mutant
	 */
	public long getCountMutantDna() {

		log.info("GET redis key: {}", redisProperties.getCountMutantDna());

		Jedis redisInstance = null;
		long countMutantDna = 0;

		try {

			redisInstance = getConnectionFromPool();

			final String sCountMutantDna = redisInstance.get(redisProperties.getCountMutantDna());
			log.debug("sCountMutantDna: {}", sCountMutantDna);

			if (StringUtils.isNotBlank(sCountMutantDna)) {
				countMutantDna = Long.parseLong(sCountMutantDna);
			}

		} catch (Exception ex) {
			log.error(StatisticRedisDao.ERROR_REDIS, ex);

		} finally {
			closeConnection(redisInstance);
		}

		log.info("countMutantDna: {}", countMutantDna);

		return countMutantDna;
	}

	/**
	 * increment the value of the key in redis where the number of DNA sequences
	 * detected as mutant is stored
	 */
	@Synchronized
	public void incrementCountMutantDna() {

		log.info("INCR redis key: {}", redisProperties.getCountMutantDna());

		Jedis redisInstance = null;

		try {

			redisInstance = getConnectionFromPool();

			final long countMutantDna = redisInstance.incr(redisProperties.getCountMutantDna());
			log.info("countMutantDna: {}", countMutantDna);

		} catch (Exception ex) {
			log.error(StatisticRedisDao.ERROR_REDIS, ex);

		} finally {
			closeConnection(redisInstance);
		}
	}

	/**
	 * query in a key in redis the number of sequences detected as human
	 * 
	 * @return number of DNA sequences detected as human
	 */
	public long getCountHumanDna() {

		log.debug("GET countHumanDna redis key: {}", redisProperties.getCountHumanDna());

		Jedis redisInstance = null;
		long countHumanDna = 0;

		try {

			redisInstance = getConnectionFromPool();

			final String sCountHumanDna = redisInstance.get(redisProperties.getCountHumanDna());
			log.info("sCountMutantDna: {}", sCountHumanDna);

			if (StringUtils.isNotBlank(sCountHumanDna)) {
				countHumanDna = Long.parseLong(sCountHumanDna);
			}

		} catch (Exception ex) {
			log.error(StatisticRedisDao.ERROR_REDIS, ex);

		} finally {
			closeConnection(redisInstance);
		}

		log.info("countHumanDna: {}", countHumanDna);

		return countHumanDna;
	}

	/**
	 * increment the value of the key in redis where the number of DNA sequences
	 * detected as human is stored
	 */
	@Synchronized
	public void incrementCountHumanDna() {

		log.debug("INCR redis key: {}", redisProperties.getCountHumanDna());

		Jedis redisInstance = null;

		try {

			redisInstance = getConnectionFromPool();

			final long countHumanDna = redisInstance.incr(redisProperties.getCountHumanDna());
			log.info("countHumanDna: {}", countHumanDna);

		} catch (Exception ex) {
			log.error(StatisticRedisDao.ERROR_REDIS, ex);

		} finally {
			closeConnection(redisInstance);
		}
	}

}
