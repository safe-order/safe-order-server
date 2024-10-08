package kr.yz.safeorder.domain.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.yz.safeorder.domain.admin.controller.dto.AdminDto
import kr.yz.safeorder.domain.admin.service.AdminService
import kr.yz.safeorder.domain.admin.controller.dto.HeadquartersEnrollDto
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import org.springframework.data.domain.Slice
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "user", description = "로그인, 회원가입 api")
@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminService: AdminService
) {

    @Operation(
        summary = "admin 로그인",
        description = "admin 로그인 하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "로그인 성공"
    )
    @PostMapping("/login")
    fun login(@RequestBody adminDto: AdminDto): TokenDto {
        return adminService.login(adminDto)
    }

    @Operation(
        summary = "admin 회원가입",
        description = "admin 회원가입 하기"
    )
    @ApiResponse(
        responseCode = "201",
        description = "회원가입 성공"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid adminDto: AdminDto): StatusDto {
        return adminService.signup(adminDto)
    }

    @Operation(
        summary = "본사 승인",
        description = "본사 승인하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "본사 승인 됨"
    )
    @PatchMapping("/approval-headquarters-enroll")
    fun approval(@RequestParam("headquarters-enroll-id") headquartersEnrollId: String): StatusDto {
        return adminService.approvalHeadquartersEnroll(headquartersEnrollId)
    }

    @Operation(
        summary = "본사 거절",
        description = "본사 거절하기"
    )
    @ApiResponse(
        responseCode = "200",
        description = "본사 거절 됨"
    )
    @DeleteMapping("/refusal-headquarters-enroll")
    fun refusal(@RequestParam("headquarters-enroll-id") headquartersEnrollId: String): StatusDto {
        return adminService.refusalHeadquartersEnroll(headquartersEnrollId)
    }

    @Operation(
        summary = "본사 가입 리스트",
        description = "본사 가입 리스트 확인"
    )
    @ApiResponse(
        responseCode = "200",
        description = "본사 가입 리스트"
    )
    @GetMapping("/headquarters-enroll-list")
    fun getHeadquartersEnrollList(@RequestParam page: Int, @RequestParam size: Int): Slice<HeadquartersEnrollDto> {
        return adminService.getHeadquartersEnrollList(page, size)
    }

}