package kr.yz.safeorder.domain.admin.controller.dto

import kr.yz.safeorder.domain.admin.FranchisorEnrollEntity
import org.springframework.data.domain.Slice

data class FranchisorEnrollDto(
    val id: String,
    val franchisor: String
)

fun Slice<FranchisorEnrollEntity>.toDto(): Slice<FranchisorEnrollDto> {
    return this.map {
        FranchisorEnrollDto(it.id, it.franchisorId.id)
    }
}