package kr.yz.safeorder.domain.supplier.repository

import kr.yz.safeorder.domain.supplier.SupplierEntity
import org.springframework.data.repository.CrudRepository

interface SupplierRepository: CrudRepository<SupplierEntity, String> {
    fun existsByUsername(userName: String): Boolean
    fun existsByBusinessNumber(businessNumber: String): Boolean
    fun findByUsername(userName: String): SupplierEntity?
}