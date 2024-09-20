package kr.yz.safeorder.domain.headquarters.repository

import kr.yz.safeorder.domain.headquarters.entity.SupplierEnrollEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SupplierEnrollRepository: CrudRepository<SupplierEnrollEntity, String> {
    @Query("SELECT supplierEnroll FROM SupplierEnrollEntity supplierEnroll")
    fun findSliceBy(pageable: Pageable): Slice<SupplierEnrollEntity>?
}