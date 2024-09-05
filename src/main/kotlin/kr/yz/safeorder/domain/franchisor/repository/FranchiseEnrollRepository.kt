package kr.yz.safeorder.domain.franchisor.repository

import kr.yz.safeorder.domain.franchisor.FranchiseEnrollEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FranchiseEnrollRepository: CrudRepository<FranchiseEnrollEntity, String> {
    @Query("SELECT franchiseEnroll FROM FranchiseEnrollEntity franchiseEnroll")
    fun findSliceBy(pageable: Pageable): Slice<FranchiseEnrollEntity>?
}