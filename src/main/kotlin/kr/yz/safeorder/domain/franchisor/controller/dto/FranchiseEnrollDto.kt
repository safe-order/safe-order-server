package kr.yz.safeorder.domain.franchisor.controller.dto

import kr.yz.safeorder.domain.franchisor.FranchiseEnrollEntity
import org.springframework.data.domain.Slice

data class FranchiseEnrollDto(
    val id: Long,
    val franchise: Long,
    val franchisor: Long
)

fun Slice<FranchiseEnrollEntity>.toDto(): Slice<FranchiseEnrollDto> {
    return this.map {
        FranchiseEnrollDto(it.id, it.franchiseId.id, it.franchisorId.id)
    }
}