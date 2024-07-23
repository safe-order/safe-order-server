package kr.yz.safeorder.domain.user.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.user.UserEntity
import kr.yz.safeorder.domain.user.controller.dto.UserLoginResponseDto
import kr.yz.safeorder.domain.user.controller.dto.UserProfileDetailResponseDto
import kr.yz.safeorder.domain.user.controller.dto.UserSignupRequestDto
import kr.yz.safeorder.domain.user.controller.dto.toUserLoginResponseDto
import kr.yz.safeorder.domain.user.error.exception.*
import kr.yz.safeorder.domain.user.repository.UserRepository
import kr.yz.safeorder.global.dto.StatusDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    fun login(id: String, password: String): UserLoginResponseDto {
        val userData = userRepository.findByIdOrNull(id) ?: throw NotExistUserIdException

        matchesPassword(password, userData.password)

        return userData.toUserLoginResponseDto()
    }

    fun signup(signupDto: UserSignupRequestDto): StatusDto {
        val userData = UserEntity(
            id = signupDto.id,
            password = signupDto.password,
            companyName = signupDto.companyName,
            representativName = signupDto.representativName,
            representativPhone = signupDto.representativPhone,
            managerPhone = signupDto.managerPhone,
            brandName = signupDto.brandName,
            address = signupDto.address,
            detailedAddress = signupDto.detailedAddress,
            businessNumber = signupDto.businessNumber,
            businessRegistrationUrl = signupDto.businessRegistrationUrl,
            bank = signupDto.bank,
            bankNumber = signupDto.bankNumber,
            userType = signupDto.userType,
            code = NanoId.generate(12, "1234567890")
        )

        userRepository.save(userData)

        return StatusDto("OK", 200)
    }

    private fun matchesPassword(password: String, sparePassword: String) {
        if (!passwordEncoder.matches(password, sparePassword)) throw InvalidPasswordException
    }

    fun checkValidId(nickname: String): StatusDto {
        if (userRepository.existsById(nickname))
            throw ExistIdException // 이미 존재하는 아이디
        return StatusDto("OK", 200)
    }

    fun checkValidBusinessNumber(businessNumber: String): StatusDto {
        if (userRepository.existsByBusinessNumber(businessNumber))
            throw ExistBusinessNumberException // 이미 존재하는 사업자 번호
        return StatusDto("OK", 200)
    }

    fun checkValidBrandName(brandName: String): StatusDto {
        if ( userRepository.existsByBrandName(brandName))
            throw ExistBrandNameException // 이미 존재하는 브랜드명
        return StatusDto("OK", 200)
    }

    fun checkValidCompanyName(companyName: String): StatusDto {
        if ( userRepository.existsByCompanyName(companyName))
            throw ExistCompanyNameException // 이미 존재하는 본사명
        return StatusDto("OK", 200)
    }

    fun getUserProfile(id: String): UserProfileDetailResponseDto {
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