package kr.yz.safeorder.domain.user.service

import kr.yz.safeorder.domain.user.controller.dto.*
import kr.yz.safeorder.domain.user.error.exception.*
import kr.yz.safeorder.domain.user.repository.UserRepository
import kr.yz.safeorder.domain.user.type.UserType
import kr.yz.safeorder.global.dto.StatusDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    // 로그인
    fun login(id: String, password: String): UserLoginResponseDto {
        val userData = userRepository.findByIdOrNull(id) ?: throw NotExistUserIdException

        matchesPassword(password, userData.password)

        return userData.toUserLoginResponseDto()
    }

    // 회원가입
    fun signup(signupDto: UserSignupRequestDto): StatusDto {
        val userData = signupDto.toUserEntity()
        userRepository.save(userData)

        return StatusDto("OK", 200)
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw InvalidPasswordException
    }

    // 아이디 존재 여부 확인
    fun checkValidId(nickname: String): StatusDto {
        if (userRepository.existsById(nickname))
            throw ExistIdException // 이미 존재하는 아이디
        return StatusDto("OK", 200)
    }

    // 사업자 번호 여부 확인(공공데이터포털 API 이용)
    fun checkValidBusinessNumber(businessNumber: String): StatusDto {
        if (userRepository.existsByBusinessNumber(businessNumber))
            throw ExistBusinessNumberException // 이미 존재하는 사업자 번호
        return StatusDto("OK", 200)
    }

    // 브랜드 이름 여부 확인
    fun checkValidBrandName(brandName: String): StatusDto {
        if (userRepository.existsByBrandName(brandName))
            throw ExistBrandNameException // 이미 존재하는 브랜드명
        return StatusDto("OK", 200)
    }

    // 본사 이름 여부 확인
    fun checkValidCompanyName(companyName: String): StatusDto {
        if (userRepository.existsByCompanyName(companyName))
            throw ExistCompanyNameException // 이미 존재하는 본사명
        return StatusDto("OK", 200)
    }

    // 유저 정보 가져오기
    fun getUserProfile(id: String): UserProfileDetailDto {
        val user = userRepository.findByIdOrNull(id) ?: throw NotExistUserIdException
        return user.toUserProfileDetailResponseDto()
    }

    // 유저 수정
    fun fixUserProfile(fixUserData: UserProfileDetailDto): UserProfileDetailDto {
        val userData = userRepository.findByIdOrNull(fixUserData.id) ?: throw NotExistUserIdException
        if (userData.userType != UserType.AGENCY) throw TODO("권한 없음")
        with(userData) {
            brandName = fixUserData.brandName
            companyName = fixUserData.companyName
            address = fixUserData.address
            detailedAddress = fixUserData.detailedAddress
            businessNumber = fixUserData.businessNumber
            bank = fixUserData.bank
            bankNumber = fixUserData.bankNumber
        }

        return userData.toUserProfileDetailResponseDto()
    }
}