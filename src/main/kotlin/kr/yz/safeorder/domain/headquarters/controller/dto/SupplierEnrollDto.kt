package kr.yz.safeorder.domain.headquarters.controller.dto

import kr.yz.safeorder.domain.headquarters.entity.SupplierEnrollEntity
import org.springframework.data.domain.Slice

data class SupplierEnrollDto(
    val id: Long, val supplier: Long, val headquarters: Long
)

fun Slice<SupplierEnrollEntity>.toDto(): Slice<SupplierEnrollDto> {
    return this.map {
        SupplierEnrollDto(it.id, it.supplierId.id, it.headquartersId.id)
    }
}