package kr.yz.safeorder.global.security.principle.headquarters

import kr.yz.safeorder.domain.franchisor.repository.FranchisorRepository
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomFranchisorDetailsService(
    private val franchisorRepository: FranchisorRepository
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = franchisorRepository.findByIdOrNull(userId) ?: throw InvalidTokenException
        return CustomFranchisorDetails(user.id.toString(), Authority.HEAD_QUARTERS)
    }

}