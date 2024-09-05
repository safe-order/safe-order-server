package kr.yz.safeorder.domain.admin.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.admin.AdminEntity
import kr.yz.safeorder.domain.admin.controller.dto.AdminDto
import kr.yz.safeorder.domain.admin.repository.AdminRepository
import kr.yz.safeorder.domain.admin.controller.dto.FranchisorEnrollDto
import kr.yz.safeorder.domain.admin.controller.dto.toDto
import kr.yz.safeorder.domain.admin.repository.FranchisorEnrollRepository
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
    private val franchisorEnrollRepository: FranchisorEnrollRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(userSignupDto: AdminDto): TokenDto {
        val adminData = adminRepository.findByUsername(userSignupDto.username) ?: throw TODO("존재 하지 않는 username")
        matchesPassword(userSignupDto.password, adminData.password)
        return jwtProvider.receiveToken(adminData.id, Authority.ADMIN)
    }

    fun signup(userSignupDto: AdminDto): StatusDto {

        if (adminRepository.existsByUsername(userSignupDto.username)) throw TODO("이미 존재하는 유저 아이디")

        val admin = AdminEntity(
            id = NanoId.generate(16),
            username = userSignupDto.username,
            password = userSignupDto.password
        )

        admin.hashPassword(passwordEncoder)
        adminRepository.save(admin)
        return StatusDto("Created", 201)
    }

    fun approvalFranchisorEnroll(franchisorEnrollId: String): StatusDto {
        val franchisorEnroll = franchisorEnrollRepository.findById(franchisorEnrollId).orElseThrow {
            throw TODO("존재 하지 않는 franchisorEnrollId")
        }
        franchisorEnroll.state = true
        franchisorEnrollRepository.save(franchisorEnroll)
        return StatusDto("Ok", 200)
    }

    fun refusalFranchisorEnroll(franchisorEnrollId: String): StatusDto {
        val franchisorEnroll = franchisorEnrollRepository.findById(franchisorEnrollId).orElseThrow {
            throw TODO("존재 하지 않는 franchisorEnrollId")
        }
        franchisorEnrollRepository.delete(franchisorEnroll)
        return StatusDto("Ok", 200)
    }

    fun getFranchisorEnrollList(page: Int, size: Int): Slice<FranchisorEnrollDto> {
        val pageRequest = PageRequest.of(
            page, size, Sort.by(
                Sort.Direction.DESC, "created_at"
            )
        )
        val franchisorData = franchisorEnrollRepository.findSliceBy(pageRequest) ?: throw TODO("신청된 본사 없음")
        return franchisorData.toDto()
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw TODO("InvalidPasswordException")
    }
}