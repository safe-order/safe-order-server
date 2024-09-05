package kr.yz.safeorder.global.error.exception

import kr.yz.safeorder.global.error.GlobalErrorCode
import kr.yz.safeorder.global.error.custom.CustomException

object ExistUsernameException: CustomException(
    GlobalErrorCode.EXIST_USERNAME
)