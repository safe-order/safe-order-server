package kr.yz.safeorder.domain.item

import io.viascom.nanoid.NanoId
import jakarta.persistence.*
import kr.yz.safeorder.domain.agency.AgencyEntity

@Entity
open class ItemEntity(
    @Id
    val id: String = NanoId.generate(12),
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "size", nullable = false)
    val size: String,
    @OneToOne
    @JoinColumn(name = "agency_id")
    val agency: AgencyEntity
)