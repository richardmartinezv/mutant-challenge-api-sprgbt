package com.mutant.challenge.api.tests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mutant.challenge.api.config.properties.RedisProperties;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = RedisProperties.class)
@TestPropertySource("classpath:application-unit-test.properties")
class RedisPropertiesTest {

	@Autowired
	private RedisProperties properties;

	@Test
	void configRedisPoolisNotNullTest() {

		assertNotNull(properties.getMain());
	}

	@Test
	void configRedisPoolIpTest() {

		assertNotNull(properties.getMain().getIp());
		assertEquals("34.72.243.64", properties.getMain().getIp());
	}

	@Test
	void configRedisPoolPortTest() {

		assertEquals(6379, properties.getMain().getPort());
	}

	@Test
	void configRedisPoolDefaultTimeoutTest() {

		assertEquals(2000, properties.getMain().getDefaultTimeout());
	}

	@Test
	void configRedisPoolMaxConnectionsTest() {

		assertEquals(300, properties.getMain().getMaxConnections());
	}

	@Test
	void configRedisPoolMaxIdleConnectionsTest() {

		assertEquals(30, properties.getMain().getMaxIdleConnections());
	}

	@Test
	void configRedisPoolMaxWaitMillisTest() {

		assertEquals(-1, properties.getMain().getMaxWaitMillis());
	}

	@Test
	void configRedisPoolMinEvictableIdleMillisTest() {

		assertEquals(60000, properties.getMain().getMinEvictableIdleMillis());
	}
	
	@Test
	void configRedisPoolBlockedWhenExhaustedTest() {

		assertEquals(true, properties.getMain().isBlockedWhenExhausted());
	}
	
	@Test
	void configRedisPoolFairnessTest() {

		assertEquals(true, properties.getMain().isFairness());
	}

	@Test
	void countMutantDnaTest() {

		assertNotNull(properties.getCountMutantDna());
		assertEquals("COUNT_MUTANT_DNA", properties.getCountMutantDna());
	}
	
	@Test
	void setCountMutantDnaTest() {
		
		final String countMutantDna = "COUNT_MUTANT_DNA";
		properties.setCountMutantDna(countMutantDna);
		assertEquals(countMutantDna, properties.getCountMutantDna());
	}
	
	@Test
	void countHumanDnaTest() {

		assertNotNull(properties.getCountHumanDna());
		assertEquals("COUNT_HUMAN_DNA", properties.getCountHumanDna());
	}
	
	@Test
	void setCountHumanDnaTest() {
		
		final String countHumanDna = "COUNT_HUMAN_DNA";
		properties.setCountHumanDna(countHumanDna);
		assertEquals(countHumanDna, properties.getCountHumanDna());
	}
}
