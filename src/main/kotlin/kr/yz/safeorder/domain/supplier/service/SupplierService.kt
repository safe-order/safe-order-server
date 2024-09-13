package kr.yz.safeorder.domain.supplier.service

import kr.yz.safeorder.domain.supplier.controller.dto.SupplierSignupDto
import kr.yz.safeorder.domain.supplier.repository.SupplierRepository
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.security.jwt.JwtProvider
import kr.yz.safeorder.global.type.Authority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SupplierService(
    private val supplierRepository: SupplierRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(username: String, password: String): TokenDto {
        val adminData = supplierRepository.findByUsername(username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(password, adminData.password)
        return jwtProvider.receiveToken(adminData.id.toString(), Authority.ADMIN)
    }
    fun signup(signupDto: SupplierSignupDto): StatusDto {
        return StatusDto("Created",201)
    }
    fun checkValidUsername(username: String): Boolean {
        return supplierRepository.existsByUsername(username)
    }

    fun checkValidBusinessNumber(businessNumber: String): Boolean {
        return supplierRepository.existsByBusinessNumber(businessNumber)
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}