package kr.yz.safeorder.global.error.exception

import kr.yz.safeorder.global.error.GlobalErrorCode
import kr.yz.safeorder.global.error.custom.CustomException


object RefreshTokenNotForundException: CustomException(
    GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND_EXCEPTION
)