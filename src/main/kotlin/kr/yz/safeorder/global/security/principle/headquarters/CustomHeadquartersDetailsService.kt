package kr.yz.safeorder.global.security.principle.headquarters

import kr.yz.safeorder.domain.headquarters.repository.HeadquartersRepository
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomHeadquartersDetailsService(
    private val headquartersRepository: HeadquartersRepository
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = headquartersRepository.findByIdOrNull(userId) ?: throw InvalidTokenException
        return CustomHeadquartersDetails(user.id.toString(), Authority.HEAD_QUARTERS)
    }

}