package kr.yz.safeorder.domain.headquarters.controller.dto

import kr.yz.safeorder.domain.headquarters.entity.FranchiseEnrollEntity
import org.springframework.data.domain.Slice

data class FranchiseEnrollDto(
    val id: Long,
    val franchise: Long,
    val headquarters: Long
)

fun Slice<FranchiseEnrollEntity>.toDto(): Slice<FranchiseEnrollDto> {
    return this.map {
        FranchiseEnrollDto(it.id, it.franchiseId.id, it.headquartersId.id)
    }
}