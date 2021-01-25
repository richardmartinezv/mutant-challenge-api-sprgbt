package com.mutant.challenge.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis keys properties
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

	private ConfigRedisPool main;

	private String countMutantDna = "COUNT_MUTANT_DNA";
	private String countHumanDna = "COUNT_HUMAN_DNA";

}
