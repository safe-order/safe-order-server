package kr.yz.safeorder.domain.client

import io.viascom.nanoid.NanoId
import jakarta.persistence.*
import kr.yz.safeorder.domain.agency.AgencyEntity

@Entity
open class ClientEntity(
    @Id
    val id: String = NanoId.generate(12),
    @ManyToOne
    @JoinColumn(name = "agency_id")
    val agency: AgencyEntity,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "phone", nullable = false)
    val phone: String,
    @Column(name = "address", nullable = false)
    val address: String
)