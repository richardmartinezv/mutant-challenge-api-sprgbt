package com.mutant.challenge.api.redis.dao;

import com.mutant.challenge.api.config.properties.ConfigRedisPool;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
public abstract class RedisDao {

	protected JedisPool jedisPool;

	/**
	 * Get {@link Jedis} Instance, through the connection pool, which is initialized
	 * in case its allocation is null.
	 *
	 * @return instance's redis
	 */
	protected Jedis getConnectionFromPool() {

		if (jedisPool == null) {
			initJedisPool();
		}

		return jedisPool.getResource();
	}

	/**
	 * Close redis connection.
	 *
	 * @param redisInstance instance's redis
	 */
	protected void closeConnection(final Jedis redisInstance) {

		if (redisInstance != null) {
			redisInstance.close();
		}
	}

	/**
	 * init jedis pool from configRedisPool.
	 *
	 * @param configRedisPool configuration
	 */
	protected void getJedisPool(final ConfigRedisPool configRedisPool) {

		log.debug("configRedisPool: {}", configRedisPool);

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		
		// Set Pool config from business properties
		jedisPoolConfig.setBlockWhenExhausted(configRedisPool.isBlockedWhenExhausted());
		jedisPoolConfig.setMaxWaitMillis(configRedisPool.getMaxWaitMillis());
		jedisPoolConfig.setMinEvictableIdleTimeMillis(configRedisPool.getMinEvictableIdleMillis());
		jedisPoolConfig.setMaxTotal(configRedisPool.getMaxConnections());
		jedisPoolConfig.setMaxIdle(configRedisPool.getMaxIdleConnections());

		// instance the Connection Pool
		jedisPool = new JedisPool(jedisPoolConfig, configRedisPool.getIp(), configRedisPool.getPort(),
				configRedisPool.getDefaultTimeout());
	}

	/**
	 * Initializes the connection pool with the parameters defined in the business
	 * properties.
	 *
	 * <p>
	 * The connection is instantiated in the {@link JedisPool} property of the
	 * class.
	 * </p>
	 */
	protected abstract void initJedisPool();
}
