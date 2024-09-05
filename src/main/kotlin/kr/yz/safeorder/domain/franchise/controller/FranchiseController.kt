package kr.yz.safeorder.domain.franchise.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.yz.safeorder.domain.certification.service.CertificationService
import kr.yz.safeorder.domain.franchise.controller.dto.ClientSignupDto
import kr.yz.safeorder.domain.franchise.service.FranchiseService
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "user", description = "로그인, 회원가입 api")
@RestController
@RequestMapping("/api/client")
class FranchiseController(
    private val franchiseService: FranchiseService,
    private val certificationService: CertificationService
) {
    @Operation(
        summary = "client 로그인",
        description = "client 로그인 하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "로그인 성공"
    )
    @PostMapping("/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): TokenDto {
        return franchiseService.login(username, password)
    }

    @Operation(
        summary = "client 회원가입",
        description = "client 회원가입 하기"
    )
    @ApiResponse(
        responseCode = "201",
        description = "회원가입 성공"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid clientSignupDto: ClientSignupDto): StatusDto {
        franchiseService.checkValidUsername(clientSignupDto.username)
        franchiseService.checkValidBusinessNumber(clientSignupDto.businessNumber)
        return franchiseService.signup(clientSignupDto)
    }
}