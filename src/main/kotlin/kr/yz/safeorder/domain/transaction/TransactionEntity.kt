package kr.yz.safeorder.domain.transaction

import io.viascom.nanoid.NanoId
import jakarta.persistence.*
import kr.yz.safeorder.domain.agency.AgencyEntity
import kr.yz.safeorder.domain.client.ClientEntity
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
open class TransactionEntity(
    @Id
    val id: String = NanoId.generate(12),
    @OneToOne
    @JoinColumn(name = "agency_id")
    val agency: AgencyEntity,
    @OneToOne
    @JoinColumn(name = "client_id")
    val client: ClientEntity,
    @CreatedDate
    @Column(name = "date")
    val date: LocalDateTime = LocalDateTime.now()
)