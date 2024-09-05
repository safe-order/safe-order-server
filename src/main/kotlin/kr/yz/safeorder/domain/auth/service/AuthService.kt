package kr.yz.safeorder.domain.auth.service

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import kr.yz.safeorder.domain.auth.RefreshTokenEntity
import kr.yz.safeorder.domain.auth.repository.RefreshTokenRepository
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.error.exception.InternalServerErrorException
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import kr.yz.safeorder.global.error.exception.UnexpectedTokenException
import kr.yz.safeorder.global.security.jwt.JwtProperties
import kr.yz.safeorder.global.security.jwt.JwtProvider
import kr.yz.safeorder.global.security.jwt.JwtParser
import kr.yz.safeorder.global.type.Authority
import kr.yz.safeorder.global.type.Token
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.security.Key

@Service
class AuthService(
    private val jwtProperties: JwtProperties,
    private val jwtProvider: JwtProvider,
    private val jwtParser: JwtParser,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun reissueToken(refreshToken: String, accessToken: String): TokenDto {

        // refreshToken Claims
        val refreshTokenClaims = jwtParser.getClaims(refreshToken, Token.REFRESH)

        checkValidAccessToken(accessToken, refreshTokenClaims)

        val data = checkValidRefreshToken(refreshToken)
        val tokenAuthority = Authority.valueOf(refreshTokenClaims.header["authority"].toString())

        val tokenDto = jwtProvider.receiveToken(data.id, tokenAuthority)

        val refreshTokenEntity = RefreshTokenEntity(
            token = tokenDto.refreshToken, id = data.id
        )

        refreshTokenRepository.save(refreshTokenEntity)

        return tokenDto
    }

    private fun checkValidAccessToken(accessToken: String, refreshTokenClaims: Jws<Claims>) {
        if (refreshTokenClaims.header[Header.JWT_TYPE] != JwtProvider.REFRESH || getClaims(accessToken)) throw InvalidTokenException
    }

    private fun checkValidRefreshToken(refreshToken: String) =
        refreshTokenRepository.findByToken(refreshToken) ?: throw UnexpectedTokenException

    private fun getClaims(token: String): Boolean {
        return try {
            val key: Key = Keys.hmacShaKeyFor(jwtProperties.accessSecretKey.toByteArray(StandardCharsets.UTF_8))
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            false
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> false // jwt token 만료
                else -> throw InternalServerErrorException
            }
        }
    }
}