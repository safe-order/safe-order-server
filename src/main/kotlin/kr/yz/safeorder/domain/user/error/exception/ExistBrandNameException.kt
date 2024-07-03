package kr.yz.safeorder.domain.user.error.exception

import kr.yz.safeorder.global.error.custom.CustomException
import kr.yz.safeorder.domain.user.error.UserErrorCode

object ExistBrandNameException: CustomException (
    UserErrorCode.EXIST_BRAND_NAME_EXCEPTION
)