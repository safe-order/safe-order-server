package kr.yz.safeorder.domain.admin.service

import kr.yz.safeorder.domain.admin.AdminEntity
import kr.yz.safeorder.domain.admin.controller.dto.AdminDto
import kr.yz.safeorder.domain.admin.repository.AdminRepository
import kr.yz.safeorder.domain.admin.controller.dto.HeadquartersEnrollDto
import kr.yz.safeorder.domain.admin.controller.dto.toDto
import kr.yz.safeorder.domain.admin.repository.HeadquartersEnrollRepository
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.security.jwt.JwtProvider
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val adminRepository: AdminRepository,
    private val headquartersEnrollRepository: HeadquartersEnrollRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(userSignupDto: AdminDto): TokenDto {
        val adminData = adminRepository.findByUsername(userSignupDto.username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(userSignupDto.password, adminData.password)
        return jwtProvider.receiveToken(adminData.id.toString(), Authority.ADMIN)
    }

    fun signup(userSignupDto: AdminDto): StatusDto {

        if (adminRepository.existsByUsername(userSignupDto.username)) throw TODO("이미 존재하는 유저 아이디")

        val admin = AdminEntity(
            id = 0,
            username = userSignupDto.username,
            password = userSignupDto.password
        )

        admin.hashPassword(passwordEncoder)
        adminRepository.save(admin)
        return StatusDto("Created", 201)
    }

    fun approvalHeadquartersEnroll(headquartersEnrollId: String): StatusDto {
        val headquartersEnroll = headquartersEnrollRepository.findById(headquartersEnrollId).orElseThrow {
            throw TODO("존재 하지 않는 headquartersEnrollId")
        }
        headquartersEnroll.state = true
        headquartersEnrollRepository.save(headquartersEnroll)
        return StatusDto("Ok", 200)
    }

    fun refusalHeadquartersEnroll(headquartersEnrollId: String): StatusDto {
        val headquartersEnroll = headquartersEnrollRepository.findById(headquartersEnrollId).orElseThrow {
            throw TODO("존재 하지 않는 headquartersEnrollId")
        }
        headquartersEnrollRepository.delete(headquartersEnroll)
        return StatusDto("Ok", 200)
    }

    fun getHeadquartersEnrollList(page: Int, size: Int): Slice<HeadquartersEnrollDto> {
        val pageRequest = PageRequest.of(
            page, size, Sort.by(
                Sort.Direction.DESC, "created_at"
            )
        )
        val headquartersData = headquartersEnrollRepository.findSliceBy(pageRequest) ?: throw TODO("신청된 본사 없음")
        return headquartersData.toDto()
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}