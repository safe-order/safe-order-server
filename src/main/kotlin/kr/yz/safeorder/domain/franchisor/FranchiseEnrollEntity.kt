package kr.yz.safeorder.domain.franchisor

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchise.FranchiseEntity

@Entity
@Table(name = "franchise_enroll")
data class FranchiseEnrollEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "state",columnDefinition = "BOOL")
    var state: Boolean = false,
    @Column(name = "created_at",columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "franchisor_id")
    val franchisorId: FranchisorEntity,
    @OneToOne
    @JoinColumn(name = "franchise_id")
    val franchiseId: FranchiseEntity
)
