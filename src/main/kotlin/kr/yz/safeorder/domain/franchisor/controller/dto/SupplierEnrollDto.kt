package kr.yz.safeorder.domain.franchisor.controller.dto

import kr.yz.safeorder.domain.franchisor.SupplierEnrollEntity
import org.springframework.data.domain.Slice

data class SupplierEnrollDto(
    val id: Long, val supplier: Long, val franchisor: Long
)

fun Slice<SupplierEnrollEntity>.toDto(): Slice<SupplierEnrollDto> {
    return this.map {
        SupplierEnrollDto(it.id, it.supplierId.id, it.franchisorId.id)
    }
}