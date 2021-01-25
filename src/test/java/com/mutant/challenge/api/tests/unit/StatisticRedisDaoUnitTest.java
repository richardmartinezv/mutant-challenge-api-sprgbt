package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.mutant.challenge.api.config.properties.RedisProperties;
import com.mutant.challenge.api.redis.dao.StatisticRedisDao;

@ExtendWith(MockitoExtension.class)
@Import({ StatisticRedisDao.class })
class StatisticRedisDaoUnitTest {

	@Mock
	RedisProperties redisProperties;

	@InjectMocks
	private StatisticRedisDao statisticRedisDao = new StatisticRedisDao();

	@Test
	void statisticRedisDaoInitializedCorrectlyTest() {
		assertThat(statisticRedisDao).isNotNull();
	}

	@Test
	void getCountMutantDnaTest() {

		Mockito.when(redisProperties.getCountMutantDna()).thenReturn("COUNT_MUTANT_DNA");

		long countMutantDna = statisticRedisDao.getCountMutantDna();

		assertThat(countMutantDna).isNotNull().isEqualTo(0);
	}

	@Test
	void getCountHumanDnaTest() {

		Mockito.when(redisProperties.getCountHumanDna()).thenReturn("COUNT_HUMAN_DNA");

		long countHumanDna = statisticRedisDao.getCountHumanDna();

		assertThat(countHumanDna).isNotNull().isEqualTo(0);
	}

}
