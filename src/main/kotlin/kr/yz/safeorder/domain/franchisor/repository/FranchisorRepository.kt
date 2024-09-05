package kr.yz.safeorder.domain.franchisor.repository

import kr.yz.safeorder.domain.franchisor.FranchisorEntity
import org.springframework.data.repository.CrudRepository

interface FranchisorRepository: CrudRepository<FranchisorEntity, String> {
    fun existsByUsername(username: String): Boolean
    fun existsByBusinessNumber(businessNumber: String): Boolean
    fun findByUsername(username: String): FranchisorEntity?
}