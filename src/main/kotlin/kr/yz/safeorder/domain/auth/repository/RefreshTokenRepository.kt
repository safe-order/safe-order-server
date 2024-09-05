package kr.yz.safeorder.domain.auth.repository

import kr.yz.safeorder.domain.auth.RefreshTokenEntity
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.repository.CrudRepository

@RedisHash(value = "refresh-token")
interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String> {
    fun findByToken(token: String): RefreshTokenEntity?
}