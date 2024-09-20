package kr.yz.safeorder.domain.admin.repository

import kr.yz.safeorder.domain.admin.HeadquartersEnrollEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface HeadquartersEnrollRepository: CrudRepository<HeadquartersEnrollEntity, String> {
    @Query("SELECT headquarters FROM HeadquartersEnrollEntity headquarters")
    fun findSliceBy(pageable: Pageable): Slice<HeadquartersEnrollEntity>?

    override fun existsById(id: String): Boolean
}