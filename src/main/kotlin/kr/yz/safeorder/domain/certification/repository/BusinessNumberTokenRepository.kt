package kr.yz.safeorder.domain.certification.repository

import kr.yz.safeorder.domain.certification.BusinessNumberTokenEntity
import org.springframework.data.repository.CrudRepository

interface BusinessNumberTokenRepository: CrudRepository<BusinessNumberTokenEntity, String> {
    fun existsByBusinessNumberAndToken(businessNumber: String, token: String): Boolean
}