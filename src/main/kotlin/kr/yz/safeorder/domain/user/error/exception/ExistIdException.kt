package kr.yz.safeorder.domain.user.error.exception

import kr.yz.safeorder.domain.user.error.UserErrorCode
import kr.yz.safeorder.global.error.custom.CustomException

object ExistIdException: CustomException(
    UserErrorCode.EXIST_USER_ID_EXCEPTION
)