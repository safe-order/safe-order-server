package kr.yz.safeorder.global.security.principle.admin

import kr.yz.safeorder.domain.admin.repository.AdminRepository
import kr.yz.safeorder.global.type.Authority
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomAdminDetailsService(
    private val adminRepository: AdminRepository
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = adminRepository.findByIdOrNull(userId) ?: throw InvalidTokenException
        return CustomAdminDetails(user.id.toString(), Authority.ADMIN)
    }

}