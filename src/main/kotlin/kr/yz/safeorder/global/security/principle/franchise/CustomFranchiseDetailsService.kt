package kr.yz.safeorder.global.security.principle.franchise

import kr.yz.safeorder.domain.franchise.repository.FranchiseRepository
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomFranchiseDetailsService(
    private val franchiseRepository: FranchiseRepository
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = franchiseRepository.findByIdOrNull(userId) ?: throw InvalidTokenException
        return CustomFranchiseDetails(user.id.toString(), Authority.CLIENT)
    }

}