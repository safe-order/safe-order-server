package kr.yz.safeorder.domain.user.controller

import kr.yz.safeorder.domain.user.controller.dto.UserLoginResponseDto
import kr.yz.safeorder.domain.user.controller.dto.UserSignupRequestDto
import kr.yz.safeorder.domain.user.service.UserService
import kr.yz.safeorder.global.dto.StatusDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun signup(@RequestBody signupDto: UserSignupRequestDto) {
        userService.signup(signupDto)
    }

    @PostMapping("/login")
    fun login(@RequestParam id: String, @RequestParam password: String): UserLoginResponseDto {
        return userService.login(id, password)
    }

    @GetMapping("/valid/business")
    fun checkValidBusinessNumber(@RequestParam businessNumber: String): StatusDto {
        return userService.checkValidBusinessNumber(businessNumber)
    }

    @GetMapping("/valid/id")
    fun checkValidId(@RequestParam id: String): StatusDto {
        return userService.checkValidId(id)
    }

    @GetMapping("/valid/brand")
    fun checkValidBrandName(@RequestParam brandName: String): StatusDto {
        return userService.checkValidBrandName(brandName)
    }

    @GetMapping("/valid/company")
    fun checkValidCompanyName(@RequestParam companyName: String): StatusDto {
        return userService.checkValidCompanyName(companyName)
    }
}