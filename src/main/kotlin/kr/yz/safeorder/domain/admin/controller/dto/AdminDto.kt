package kr.yz.safeorder.domain.admin.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AdminDto(
    @field:NotBlank(message = "아이디는 필수 입력 값입니다.")
    @field:Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "아이디는 4~12자 영문 대 소문자, 숫자만 사용하세요.")
    val username: String,
    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\d)(?=.*[~․!@#\$%^&*()_\\-+=|\\\\;:‘“<>,.?/]).{8,50}\$",
        message = "비밀번호는 8~50자 영문 대 소문자, 숫자, 특수문자를 사용하세요."
    )
    val password: String
)

