package kr.yz.safeorder.domain.franchisor.controller.dto

import kr.yz.safeorder.domain.franchisor.FranchiseEnrollEntity
import org.springframework.data.domain.Slice

data class FranchiseEnrollDto(
    val id: String,
    val franchise: String,
    val franchisor: String
)

fun Slice<FranchiseEnrollEntity>.toDto(): Slice<FranchiseEnrollDto> {
    return this.map {
        FranchiseEnrollDto(it.id, it.franchiseId.id, it.franchisorId.id)
    }
}