package kr.yz.safeorder.domain.auth.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.yz.safeorder.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Auth", description = "auth api")
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @Operation(
        summary = "accessToken 재발급",
        description = "accessToken 재발급 하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "재발급 성공"
    )
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/refresh")
    fun reissueToken(
        @RequestHeader("refresh-token") refreshToken: String,
        @RequestHeader("access-token") accessToken: String
    ) = authService.reissueToken(refreshToken, accessToken)
}