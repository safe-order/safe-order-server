package kr.yz.safeorder.domain.headquarters.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kr.yz.safeorder.domain.certification.dto.response.CheckValidVerifyCodeResponseDto
import kr.yz.safeorder.domain.certification.service.CertificationService
import kr.yz.safeorder.domain.headquarters.controller.dto.FranchiseEnrollDto
import kr.yz.safeorder.domain.headquarters.controller.dto.SupplierEnrollDto
import kr.yz.safeorder.domain.headquarters.controller.dto.HeadquartersSignupDto
import kr.yz.safeorder.domain.headquarters.service.HeadquartersService
import kr.yz.safeorder.global.dto.StatusDto
import kr.yz.safeorder.global.dto.TokenDto
import kr.yz.safeorder.global.error.exception.ExistUserIdException
import kr.yz.safeorder.global.security.principle.headquarters.CustomHeadquartersDetails
import org.springframework.data.domain.Slice
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "user", description = "로그인, 회원가입 api")
@RestController
@RequestMapping("/api/headquarters")
class HeadquartersController(
    private val headquartersService: HeadquartersService, private val certificationService: CertificationService
) {

    @Operation(
        summary = "headquarters 로그인", description = "headquarters 로그인 하기"
    )
    @ApiResponse(
        responseCode = "200", description = "로그인 성공"
    )
    @PostMapping("/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): TokenDto {
        return headquartersService.login(username, password)
    }

    @Operation(
        summary = "headquarters 회원가입", description = "headquarters 회원가입 하기"
    )
    @ApiResponse(
        responseCode = "201", description = "회원가입 성공"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody headquartersSignupDto: HeadquartersSignupDto): StatusDto {
        headquartersService.checkValidUsername(headquartersSignupDto.username)
        certificationService.checkValidBusinessNumberToken(headquartersSignupDto.businessNumberToken, headquartersSignupDto.businessNumber)
        return headquartersService.signup(headquartersSignupDto)
    }

    @Operation(
        summary = "username 유효성 확인", description = "username 유효성 확인 하기"
    )
    @ApiResponse(
        responseCode = "200", description = "사용가능한 아이디"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/valid-username")
    fun validUsername(@RequestParam username: String): StatusDto {
        if (headquartersService.checkValidUsername(username)) {
            return StatusDto("Ok", 200)
        }
        throw ExistUserIdException
    }

    @Operation(
        summary = "사업자 번호 유효성 확인", description = "사업자 번호 유효성 확인 하기"
    )
    @ApiResponse(
        responseCode = "200", description = "사용가능한 사업자 번호"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/valid-business-number")
    fun validBusinessNumber(@RequestParam("business-number") businessNumber: String): CheckValidVerifyCodeResponseDto {
        if (headquartersService.checkValidBusinessNumber(businessNumber)) {
            return certificationService.checkValidBusinessNumber(businessNumber)
        }
        throw ExistUserIdException
    }

    @Operation(
        summary = "가맹점 승인", description = "가맹점 승인 하기"
    )
    @ApiResponse(
        responseCode = "200", description = "가맹점 승인됨"
    )
    @PostMapping("/approval-franchise-enroll")
    fun approvalFranchiseEnroll(@RequestParam("franchise-enroll-id") franchiseEnrollId: String, @AuthenticationPrincipal auth: CustomHeadquartersDetails): StatusDto {
        return headquartersService.approvalFranchiseEnroll(franchiseEnrollId)
    }

    @Operation(
        summary = "납품점 승인", description = "납품점 승인하기"
    )
    @ApiResponse(
        responseCode = "200", description = "납품점 승인 됨"
    )
    @PostMapping("/approval-supplier-enroll")
    fun approvalSupplierEnroll(@RequestParam("franchise-enroll-id") franchiseEnrollId: String): StatusDto {
        return headquartersService.approvalSupplierEnroll(franchiseEnrollId)
    }

    @Operation(
        summary = "납품점 가입 리스트", description = "납품점 가입 리스트 확인"
    )
    @ApiResponse(
        responseCode = "200", description = "납품점 가입 리스트"
    )
    @GetMapping("/supplier-enroll-list")
    fun supplierList(@RequestParam page: Int, @RequestParam size: Int): Slice<SupplierEnrollDto> {
        return headquartersService.supplierEnrollList(page, size)
    }

    @Operation(
        summary = "가맹점 가입 리스트", description = "가맹점 가입 리스트 확인"
    )
    @ApiResponse(
        responseCode = "200", description = "가맹점 가입 리스트"
    )
    @GetMapping("/franchise-enroll-list")
    fun franchiseList(@RequestParam page: Int, @RequestParam size: Int): Slice<FranchiseEnrollDto> {
        return headquartersService.franchiseEnrollList(page, size)
    }

}