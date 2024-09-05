package kr.yz.safeorder.global.error.exception

import kr.yz.safeorder.global.error.GlobalErrorCode
import kr.yz.safeorder.global.error.custom.CustomException

object ExistUserIdException: CustomException(
    GlobalErrorCode.EXIST_USER_ID
)