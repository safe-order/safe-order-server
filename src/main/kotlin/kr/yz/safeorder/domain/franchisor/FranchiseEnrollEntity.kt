package kr.yz.safeorder.domain.franchisor

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchise.FranchiseEntity

@Entity
@Table(name = "franchise_enroll")
data class FranchiseEnrollEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String,
    @Column(name = "state",columnDefinition = "BOOL")
    var state: Boolean = false,
    @Column(name = "created_at",columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "franchisor_id", columnDefinition = "CHAR(16)")
    val franchisorId: FranchisorEntity,
    @OneToOne
    @JoinColumn(name = "franchise_id", columnDefinition = "CHAR(16)")
    val franchiseId: FranchiseEntity
)
