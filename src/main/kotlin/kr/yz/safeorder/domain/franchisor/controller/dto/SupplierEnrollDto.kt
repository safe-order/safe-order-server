package kr.yz.safeorder.domain.franchisor.controller.dto

import kr.yz.safeorder.domain.franchisor.SupplierEnrollEntity
import org.springframework.data.domain.Slice

data class SupplierEnrollDto(
    val id: String, val supplier: String, val franchisor: String
)

fun Slice<SupplierEnrollEntity>.toDto(): Slice<SupplierEnrollDto> {
    return this.map {
        SupplierEnrollDto(it.id, it.supplierId.id, it.franchisorId.id)
    }
}