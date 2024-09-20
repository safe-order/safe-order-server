package kr.yz.safeorder.domain.headquarters.repository

import kr.yz.safeorder.domain.headquarters.entity.HeadquartersEntity
import org.springframework.data.repository.CrudRepository

interface HeadquartersRepository: CrudRepository<HeadquartersEntity, String> {
    fun existsByUsername(username: String): Boolean
    fun existsByBusinessNumber(businessNumber: String): Boolean
    fun findByUsername(username: String): HeadquartersEntity?
}