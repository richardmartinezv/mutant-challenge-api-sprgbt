package com.mutant.challenge.api.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Redis Pool configuration properties
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Getter
@Setter
public final class ConfigRedisPool {

	private String ip = "34.72.243.64";
	private int port = 6379;
	private boolean blockedWhenExhausted = true;
	private boolean fairness = true;
	private int defaultTimeout = 2000;
	private int maxConnections = 300;
	private int maxIdleConnections = 30;
	private int maxWaitMillis = -1;
	private int minEvictableIdleMillis = 60000;

}
