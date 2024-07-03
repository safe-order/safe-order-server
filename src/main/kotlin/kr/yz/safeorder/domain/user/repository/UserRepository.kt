package kr.yz.safeorder.domain.user.repository

import kr.yz.safeorder.domain.user.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, String> {
    override fun existsById(id: String): Boolean
    fun existsByBusinessNumber(businessCode: String): Boolean
    fun existsByBrandName(brandName: String): Boolean
    fun existsByCompanyName(companyName: String): Boolean
}