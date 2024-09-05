package kr.yz.safeorder.global.error.exception

import kr.yz.safeorder.global.error.GlobalErrorCode
import kr.yz.safeorder.global.error.custom.CustomException


object InternalServerErrorException: CustomException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
)