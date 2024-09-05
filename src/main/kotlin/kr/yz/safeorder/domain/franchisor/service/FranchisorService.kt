package kr.yz.safeorder.domain.franchisor.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.franchisor.FranchisorEntity
import kr.yz.safeorder.domain.franchisor.controller.dto.FranchiseEnrollDto
import kr.yz.safeorder.domain.franchisor.controller.dto.SupplierEnrollDto
import kr.yz.safeorder.domain.franchisor.controller.dto.FranchisorSignupDto
import kr.yz.safeorder.domain.franchisor.controller.dto.toDto
import kr.yz.safeorder.domain.franchisor.repository.FranchiseEnrollRepository
import kr.yz.safeorder.domain.franchisor.repository.FranchisorRepository
import kr.yz.safeorder.domain.franchisor.repository.SupplierEnrollRepository
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.security.jwt.JwtProvider
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FranchisorService(
    private val franchisorRepository: FranchisorRepository,
    private val franchiseEnrollRepository: FranchiseEnrollRepository,
    private val supplierEnrollRepository: SupplierEnrollRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(username: String, password: String): TokenDto {
        val adminData = franchisorRepository.findByUsername(username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(password, adminData.password)
        return jwtProvider.receiveToken(adminData.id, Authority.ADMIN)
    }

    fun signup(signupDto: FranchisorSignupDto): StatusDto {
        val franchisorData = FranchisorEntity(
            id = NanoId.generate(16),
            username = signupDto.username,
            password = signupDto.password,
            createdAt = LocalDateTime.now(),
            address = signupDto.address,
            detailedAddress = signupDto.detailedAddress,
            companyName = signupDto.companyName,
            representativePhone = signupDto.representativePhone,
            businessNumber = signupDto.businessNumber,
            businessRegistrationUrl = ""
        )

        franchisorData.hashPassword(passwordEncoder)
        franchisorRepository.save(franchisorData)
        return StatusDto("Created", 201)
    }

    fun approvalFranchiseEnroll(franchiseEnrollId: String): StatusDto {
        val franchiseEnrollData = franchiseEnrollRepository.findById(franchiseEnrollId).orElseThrow {
            throw TODO("존재하지 않는 franchiseEnrollId")
        }
        franchiseEnrollData.state = true
        franchiseEnrollRepository.save(franchiseEnrollData)
        return StatusDto("Ok", 200)
    }

    fun approvalSupplierEnroll(supplierEnrollId: String): StatusDto {
        return StatusDto("Ok", 200)
    }

    fun refusalFranchiseEnroll(franchiseEnrollId: String): StatusDto {
        return StatusDto("Ok", 200)
    }

    fun refusalSupplierEnroll(supplierEnrollId: String): StatusDto {
        return StatusDto("Ok", 200)
    }

    fun supplierEnrollList(page: Int, size: Int): Slice<SupplierEnrollDto> {

        val pageRequest = PageRequest.of(
            page, size, Sort.by(
                Sort.Direction.DESC, "createdAt"
            )
        )
        val supplierEnrollData = supplierEnrollRepository.findSliceBy(pageRequest) ?: throw TODO("supplierEnroll 없음")
        return supplierEnrollData.toDto()
    }

    fun franchiseEnrollList(page: Int, size: Int): Slice<FranchiseEnrollDto> {
        val pageRequest = PageRequest.of(
            page, size, Sort.by(
                Sort.Direction.DESC, "createdAt"
            )
        )
        val franchiseEnrollData = franchiseEnrollRepository.findSliceBy(pageRequest) ?: throw TODO("franchiseEnroll 없음")
        return franchiseEnrollData.toDto()
    }

    fun checkValidUsername(username: String): Boolean {
        return franchisorRepository.existsByUsername(username)
    }

    fun checkValidBusinessNumber(businessNumber: String): Boolean {
        return franchisorRepository.existsByBusinessNumber(businessNumber)
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}