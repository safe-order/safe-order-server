package kr.yz.safeorder.domain.user.controller

import kr.yz.safeorder.domain.user.controller.dto.UserLoginResponseDto
import kr.yz.safeorder.domain.user.controller.dto.UserProfileDetailDto
import kr.yz.safeorder.domain.user.controller.dto.UserSignupRequestDto
import kr.yz.safeorder.domain.user.service.UserService
import kr.yz.safeorder.global.dto.StatusDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

    // 회원가입
    @PostMapping("/register")
    fun signup(@RequestBody signupDto: UserSignupRequestDto) {
        userService.signup(signupDto)
    }

    // 로그인
    @PostMapping("/login")
    fun login(@RequestParam id: String, @RequestParam password: String): UserLoginResponseDto {
        return userService.login(id, password)
    }

    // 사업자 번호 유무(공공데이터포털 API 이용)
    @GetMapping("/valid/business")
    fun checkValidBusinessNumber(@RequestParam businessNumber: String): StatusDto {
        return userService.checkValidBusinessNumber(businessNumber)
    }

    // 아이디 존재 여부
    @GetMapping("/valid/id")
    fun checkValidId(@RequestParam id: String): StatusDto {
        return userService.checkValidId(id)
    }

    // 브랜드 이름 존재 여부
    @GetMapping("/valid/brand")
    fun checkValidBrandName(@RequestParam brandName: String): StatusDto {
        return userService.checkValidBrandName(brandName)
    }

    // 본사 이름 존재 여부
    @GetMapping("/valid/company")
    fun checkValidCompanyName(@RequestParam companyName: String): StatusDto {
        return userService.checkValidCompanyName(companyName)
    }

    // 프로필 가져오기
    @GetMapping("/profile")
    fun getUserProfile(@RequestParam id: String): UserProfileDetailDto {
        return userService.getUserProfile(id)
    }

    // 유저 수정
    @PatchMapping("/profile")
    fun fixUsrProfile(@RequestBody userData: UserProfileDetailDto): UserProfileDetailDto {
        return userService.fixUserProfile(userData)
    }
}