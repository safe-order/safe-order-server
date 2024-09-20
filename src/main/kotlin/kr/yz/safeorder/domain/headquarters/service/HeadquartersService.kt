package kr.yz.safeorder.domain.headquarters.service

import kr.yz.safeorder.domain.headquarters.entity.HeadquartersEntity
import kr.yz.safeorder.domain.headquarters.controller.dto.FranchiseEnrollDto
import kr.yz.safeorder.domain.headquarters.controller.dto.SupplierEnrollDto
import kr.yz.safeorder.domain.headquarters.controller.dto.HeadquartersSignupDto
import kr.yz.safeorder.domain.headquarters.controller.dto.toDto
import kr.yz.safeorder.domain.headquarters.repository.FranchiseEnrollRepository
import kr.yz.safeorder.domain.headquarters.repository.HeadquartersRepository
import kr.yz.safeorder.domain.headquarters.repository.SupplierEnrollRepository
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
class HeadquartersService(
    private val headquartersRepository: HeadquartersRepository,
    private val franchiseEnrollRepository: FranchiseEnrollRepository,
    private val supplierEnrollRepository: SupplierEnrollRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(username: String, password: String): TokenDto {
        val adminData = headquartersRepository.findByUsername(username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(password, adminData.password)
        return jwtProvider.receiveToken(adminData.id.toString(), Authority.ADMIN)
    }

    fun signup(signupDto: HeadquartersSignupDto): StatusDto {
        val headquartersData = HeadquartersEntity(
            id = 0,
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

        headquartersData.hashPassword(passwordEncoder)
        headquartersRepository.save(headquartersData)
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
        return headquartersRepository.existsByUsername(username)
    }

    fun checkValidBusinessNumber(businessNumber: String): Boolean {
        return headquartersRepository.existsByBusinessNumber(businessNumber)
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}