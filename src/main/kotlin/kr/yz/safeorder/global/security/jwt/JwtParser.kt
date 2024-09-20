package kr.yz.safeorder.global.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import kr.yz.safeorder.global.error.exception.*
import kr.yz.safeorder.global.security.principle.admin.CustomAdminDetailsService
import kr.yz.safeorder.global.security.principle.franchise.CustomFranchiseDetailsService
import kr.yz.safeorder.global.security.principle.headquarters.CustomHeadquartersDetailsService
import kr.yz.safeorder.global.security.principle.supplier.CustomSupplierDetailsService
import kr.yz.safeorder.global.type.Authority
import kr.yz.safeorder.global.type.Token
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtParser(
    private val jwtProperties: JwtProperties,
    private val adminDetailsService: CustomAdminDetailsService,
    private val clientDetailsService: CustomFranchiseDetailsService,
    private val headQuartersDetails: CustomHeadquartersDetailsService,
    private val supplierDetailsService: CustomSupplierDetailsService,
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token, Token.ACCESS)

        if (claims.header[Header.JWT_TYPE] != JwtProvider.ACCESS) throw InvalidTokenException
        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getClaims(token: String, tokenType: Token): Jws<Claims> {
        return try {
            val key = when (tokenType) {
                Token.ACCESS -> Keys.hmacShaKeyFor(jwtProperties.accessSecretKey.toByteArray(StandardCharsets.UTF_8))
                Token.REFRESH -> Keys.hmacShaKeyFor(jwtProperties.refreshSecretKey.toByteArray(StandardCharsets.UTF_8))
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw UnexpectedTokenException
                else -> throw InternalServerErrorException
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {
        return when (body["authority"].toString()) {
            Authority.ADMIN.name -> adminDetailsService.loadUserByUsername(body.id)
            Authority.CLIENT.name -> clientDetailsService.loadUserByUsername(body.id)
            Authority.HEAD_QUARTERS.name -> headQuartersDetails.loadUserByUsername(body.id)
            Authority.SUPPLIER.name -> supplierDetailsService.loadUserByUsername(body.id)
            else -> throw InvalidRoleException
        }
    }
}