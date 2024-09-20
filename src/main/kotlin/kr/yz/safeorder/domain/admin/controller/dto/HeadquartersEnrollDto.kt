package kr.yz.safeorder.domain.admin.controller.dto

import kr.yz.safeorder.domain.admin.HeadquartersEnrollEntity
import org.springframework.data.domain.Slice

data class HeadquartersEnrollDto(
    val id: Long,
    val headquarters: Long
)

fun Slice<HeadquartersEnrollEntity>.toDto(): Slice<HeadquartersEnrollDto> {
    return this.map {
        HeadquartersEnrollDto(it.id, it.headquartersId.id)
    }
}