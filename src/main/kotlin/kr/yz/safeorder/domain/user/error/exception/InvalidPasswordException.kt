package kr.yz.safeorder.domain.user.error.exception

import kr.yz.safeorder.domain.user.error.UserErrorCode
import kr.yz.safeorder.global.error.custom.CustomException


object InvalidPasswordException: CustomException(
    UserErrorCode.INVALID_PASSWORD_EXCEPTION
)