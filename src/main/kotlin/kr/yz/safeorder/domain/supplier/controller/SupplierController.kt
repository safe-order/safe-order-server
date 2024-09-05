package kr.yz.safeorder.domain.supplier.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.yz.safeorder.domain.supplier.controller.dto.SupplierSignupDto
import kr.yz.safeorder.domain.supplier.service.SupplierService
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "user", description = "로그인, 회원가입 api")
@RestController
class SupplierController(
    private val supplierService: SupplierService
) {
    @Operation(
        summary = "supplier 로그인",
        description = "supplier 로그인 하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "로그인 성공"
    )
    @PostMapping("/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): TokenDto {
        return supplierService.login(username, password)
    }

    @Operation(
        summary = "supplier 회원가입",
        description = "supplier 회원가입 하기"
    )
    @ApiResponse(
        responseCode = "201",
        description = "회원가입 성공"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody supplierSignupDto: SupplierSignupDto): StatusDto {
        return supplierService.signup(supplierSignupDto)
    }

}