package kr.yz.safeorder.domain.headquarters.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class HeadquartersSignupDto(
    @field:NotBlank(message = "아이디는 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{4,12}$",
        message = "아이디는 4~12자 영문 대 소문자, 숫자만 사용하세요."
    )
    val username: String, // 아이디
    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\d)(?=.*[~․!@#\$%^&*()_\\-+=|\\\\;:‘“<>,.?/]).{8,50}\$",
        message = "비밀번호는 8~50자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
    )
    val password: String, // 비밀번호
    @field:NotBlank(message = "주소는 필수 입력 값입니다.")
    var address: String, // 주소
    @field:NotBlank(message = "상세 주소는 필수 입력 값입니다.")
    var detailedAddress: String, // 상세 주소
    @field:NotBlank(message = "회사이름은 필수 입력 값입니다.")
    var companyName: String, // 회사 이름
    @field:NotBlank(message = "대표자 전화번호는 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^\\d{3}-\\d{4}-\\d{4}\$",
        message = "대표자 전화번호는 000-0000-0000 사용하세요."
    )
    val representativePhone: String, // 대표자 전화번호
    @field:NotBlank(message = "사업자 번호는 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^\\d{3}-\\d{2}-\\d{5}\$",
        message = "사업자 번호는 000-00-00000 이렇게 작성해주세요."
    )
    var businessNumber: String, // 사업자 번호
    @field:Pattern(
        regexp = "^{8,}$",
        message = "사업자 번호 토큰은 12글자 입니다."
    )
    val businessNumberToken: String
)