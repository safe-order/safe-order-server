package kr.yz.safeorder.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") private val redisHost: String,
    @Value("\${spring.data.redis.port}") private val redisPort: Int,
    @Value("\${spring.data.redis.password}") private val password: String
) {
    fun redisConnectionFactory(dbIndex: Int): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = redisHost
        redisStandaloneConfiguration.port = redisPort
        redisStandaloneConfiguration.password = RedisPassword.of(password)
        redisStandaloneConfiguration.database = dbIndex

        val lettuceConnectionFactory = LettuceConnectionFactory(redisStandaloneConfiguration)
        return lettuceConnectionFactory
    }

    @Bean
    @Primary
    fun refreshTokenConnectionFactory(): RedisConnectionFactory {
        return redisConnectionFactory(0)
    }

    @Bean
    fun refreshTokenRedisTemplate(refreshTokenConnectionFactory: RedisConnectionFactory): StringRedisTemplate {
        return StringRedisTemplate(refreshTokenConnectionFactory)
    }


}