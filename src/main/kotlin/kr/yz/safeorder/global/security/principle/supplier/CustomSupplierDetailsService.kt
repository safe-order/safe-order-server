package kr.yz.safeorder.global.security.principle.supplier

import kr.yz.safeorder.domain.supplier.repository.SupplierRepository
import kr.yz.safeorder.global.error.exception.InvalidTokenException
import kr.yz.safeorder.global.type.Authority
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomSupplierDetailsService(
    private val supplierRepository: SupplierRepository
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = supplierRepository.findByIdOrNull(userId) ?: throw InvalidTokenException
        return CustomSupplierDetails(user.id.toString(), Authority.SUPPLIER)
    }

}