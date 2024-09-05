package kr.yz.safeorder.domain.certification.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import kr.yz.safeorder.domain.certification.dto.response.CheckValidVerifyCodeResponseDto
import kr.yz.safeorder.domain.certification.service.CertificationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/certification")
class CertificationController(
    private val certificationService: CertificationService
) {

    @Operation(
        summary = "사업자 번호 인증",
        description = "사업자 번호 인증 하기(test 사업자 번호 124-81-00998)"
    )
    @ApiResponse(
        responseCode = "200",
        description = "사업자 번호 인증확인"
    )
    @PostMapping("/verify")
    suspend fun verify(
        @RequestParam("business-number") @Pattern(
            regexp = "^\\d{10}\$", message = "only 사업자 번호"
        ) @Valid businessNumber: String
    ): CheckValidVerifyCodeResponseDto {
        return certificationService.checkValidBusinessNumber(businessNumber)
    }

}