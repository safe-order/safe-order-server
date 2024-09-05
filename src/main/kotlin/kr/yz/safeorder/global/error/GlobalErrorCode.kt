package kr.yz.safeorder.global.error


import kr.yz.safeorder.global.error.custom.CustomErrorProperty


enum class GlobalErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {
    EXIST_USER_ID_EXCEPTION(409, "아이디가 이미 존재합니다."),
    EXIST_BUSINESS_NUMBER_EXCEPTION(409, "사업자 번호가 이미 존재합니다."),
    INVALID_PASSWORD_EXCEPTION(409, "유효하지 않는 비밀번호입니다."),
    NOT_EXIST_ID(409, "존재하지 않는 아이디입니다."),
    REFRESH_TOKEN_NOT_FOUND_EXCEPTION(400, "RefreshToken이 존재하지 않음"),
    INTERNAL_SERVER_ERROR(500, "서버 애러"), //서버 애러
    NOT_EXIST_USER_ID(409, "존재 하지 않는 유저 아이디 입니다."),
    NOT_EXIST_USERNAME(409, "존재 하지 않는 닉네임 입니다."),
    EXIST_USER_ID(409, "유저 아이디가 이미 존재합니다"),
    EXIST_USERNAME(409, "닉네임이 이미 존재합니다"),
    INVALID_TOKEN(401, "잘못된 토큰"), //잘못된 토큰
    EXPIRED_TOKEN(401, "만료된 토큰"), //만료
    UNEXPECTED_TOKEN(401, "Unexpected Token"), //무단
    INVALID_ROLE(403, "권한 없음"), //권한 없음
    ;

    override fun status(): Int = status
    override fun message(): String = message
}