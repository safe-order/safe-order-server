package kr.yz.safeorder.domain.franchise.service

import kr.yz.safeorder.domain.franchise.controller.dto.ClientSignupDto
import kr.yz.safeorder.domain.franchise.repository.FranchiseRepository
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.security.jwt.JwtProvider
import kr.yz.safeorder.global.type.Authority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class FranchiseService(
    private val franchiseRepository: FranchiseRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(username: String, password: String): TokenDto {
        val adminData = franchiseRepository.findByUsername(username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(password, adminData.password)
        return jwtProvider.receiveToken(adminData.id.toString(), Authority.ADMIN)
    }

    fun signup(signupDto: ClientSignupDto): StatusDto {
        return StatusDto("Created", 201)
    }

    fun checkValidUsername(username: String) {
        if(!franchiseRepository.existsByUsername(username))
            throw TODO("이미 존재하는 username")
    }

    fun checkValidBusinessNumber(businessNumber: String) {
        if(!franchiseRepository.existsByBusinessNumber(businessNumber))
            throw TODO("이미 존재하는 businessNumber")
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}