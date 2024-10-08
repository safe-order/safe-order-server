package kr.yz.safeorder.global.dto

import java.time.LocalDateTime

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: LocalDateTime,
    val authority: String
)
