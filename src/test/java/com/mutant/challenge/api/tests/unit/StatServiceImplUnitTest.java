package com.mutant.challenge.api.tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.mutant.challenge.api.model.response.StatsResponse;
import com.mutant.challenge.api.redis.dao.StatisticRedisDao;
import com.mutant.challenge.api.service.IStatService;
import com.mutant.challenge.api.service.impl.StatServiceImpl;

@ExtendWith(MockitoExtension.class)
@Import({ IStatService.class })
class StatServiceImplUnitTest {

	@Mock
	StatisticRedisDao statisticRedisDao;

	@InjectMocks
	private IStatService statService = new StatServiceImpl();
	
	@Test
	void statServiceInitializedCorrectlyTest() {
		assertThat(statService).isNotNull();
	}
	
	@Test
	void getVerificationStatisticTest() {

		final long countMutantDna = 1;
		final long countHumanDna = 3;
		final double ratio = 0.33;

		Mockito.when(statisticRedisDao.getCountMutantDna()).thenReturn(countMutantDna);
		Mockito.when(statisticRedisDao.getCountHumanDna()).thenReturn(countHumanDna);

		StatsResponse statsResponse = statService.getVerificationStatistic();

		assertThat(statsResponse).isNotNull().hasFieldOrPropertyWithValue("countMutantDna", countMutantDna)
				.hasFieldOrPropertyWithValue("countHumanDna", countHumanDna)
				.hasFieldOrPropertyWithValue("ratio", ratio);
	}
	
	@Test
	void getVerificationStatisticCountersAtZeroTest() {

		final long countMutantDna = 0;
		final long countHumanDna = 0;
		final double ratio = 0.0;

		Mockito.when(statisticRedisDao.getCountMutantDna()).thenReturn(countMutantDna);
		Mockito.when(statisticRedisDao.getCountHumanDna()).thenReturn(countHumanDna);

		StatsResponse statsResponse = statService.getVerificationStatistic();

		assertThat(statsResponse).isNotNull().hasFieldOrPropertyWithValue("countMutantDna", countMutantDna)
				.hasFieldOrPropertyWithValue("countHumanDna", countHumanDna)
				.hasFieldOrPropertyWithValue("ratio", ratio);
	}
	
	@Test
	void getVerificationStatisticCountMutantDnaZeroTest() {

		final long countMutantDna = 0;
		final long countHumanDna = 1;
		final double ratio = 0.0;

		Mockito.when(statisticRedisDao.getCountMutantDna()).thenReturn(countMutantDna);
		Mockito.when(statisticRedisDao.getCountHumanDna()).thenReturn(countHumanDna);

		StatsResponse statsResponse = statService.getVerificationStatistic();

		assertThat(statsResponse).isNotNull().hasFieldOrPropertyWithValue("countMutantDna", countMutantDna)
				.hasFieldOrPropertyWithValue("countHumanDna", countHumanDna)
				.hasFieldOrPropertyWithValue("ratio", ratio);
	}
	
	@Test
	void getVerificationStatisticCountHumanDnaZeroTest() {

		final long countMutantDna = 1;
		final long countHumanDna = 0;
		final double ratio = 0.0;

		Mockito.when(statisticRedisDao.getCountMutantDna()).thenReturn(countMutantDna);
		Mockito.when(statisticRedisDao.getCountHumanDna()).thenReturn(countHumanDna);

		StatsResponse statsResponse = statService.getVerificationStatistic();

		assertThat(statsResponse).isNotNull().hasFieldOrPropertyWithValue("countMutantDna", countMutantDna)
				.hasFieldOrPropertyWithValue("countHumanDna", countHumanDna)
				.hasFieldOrPropertyWithValue("ratio", ratio);
	}

}
