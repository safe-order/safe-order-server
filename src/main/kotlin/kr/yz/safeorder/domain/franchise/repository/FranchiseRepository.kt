package kr.yz.safeorder.domain.franchise.repository

import kr.yz.safeorder.domain.franchise.FranchiseEntity
import org.springframework.data.repository.CrudRepository

interface FranchiseRepository: CrudRepository<FranchiseEntity, String> {
    fun existsByUsername(username: String): Boolean
    fun existsByBusinessNumber(businessNumber: String): Boolean
    fun findByUsername(username: String): FranchiseEntity?
}