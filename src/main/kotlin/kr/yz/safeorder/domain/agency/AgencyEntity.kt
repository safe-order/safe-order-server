package kr.yz.safeorder.domain.agency

import io.viascom.nanoid.NanoId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
open class AgencyEntity(
    @Id
    val id: String = NanoId.generate(12),
    @Column(name = "business_number", nullable = false)
    val businessNumber: String,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "phone", nullable = false)
    val phone: String,
    @Column(name = "address", nullable = false)
    val address: String
)