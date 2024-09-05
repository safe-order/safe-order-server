package kr.yz.safeorder.domain.certification

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "businessnumber_token", timeToLive = 600)
data class BusinessNumberTokenEntity(
    @Id
    val businessNumber: String,
    @Indexed
    val token: String
)
