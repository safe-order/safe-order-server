package kr.yz.safeorder.domain.user.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.user.UserEntity
import kr.yz.safeorder.domain.user.controller.dto.UserLoginResponseDto
import kr.yz.safeorder.domain.user.controller.dto.UserSignupRequestDto
import kr.yz.safeorder.domain.user.controller.dto.toUserLoginResponseDto
import kr.yz.safeorder.domain.user.repository.UserRepository
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.domain.user.error.exception.ExistBusinessNumberException
import kr.yz.safeorder.domain.user.error.exception.ExistIdException
import kr.yz.safeorder.domain.user.error.exception.InvalidPasswordException
import kr.yz.safeorder.domain.user.error.exception.NotExistUserIdException
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
            back = signupDto.back,
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
}