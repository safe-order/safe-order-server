package kr.yz.safeorder.domain.admin.repository

import kr.yz.safeorder.domain.admin.AdminEntity
import org.springframework.data.repository.CrudRepository

interface AdminRepository: CrudRepository<AdminEntity, String> {
    fun findByUsername(username: String): AdminEntity?
    fun existsByUsername(username: String): Boolean
}