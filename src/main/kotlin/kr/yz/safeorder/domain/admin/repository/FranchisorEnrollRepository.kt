package kr.yz.safeorder.domain.admin.repository

import kr.yz.safeorder.domain.admin.FranchisorEnrollEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface FranchisorEnrollRepository: CrudRepository<FranchisorEnrollEntity, String> {
    @Query("SELECT franchisor FROM FranchisorEnrollEntity franchisor")
    fun findSliceBy(pageable: Pageable): Slice<FranchisorEnrollEntity>?

    override fun existsById(id: String): Boolean
}