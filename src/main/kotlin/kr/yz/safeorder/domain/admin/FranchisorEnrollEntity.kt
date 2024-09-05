package kr.yz.safeorder.domain.admin

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchisor.FranchisorEntity

@Entity
@Table(name = "franchisor_enroll")
data class FranchisorEnrollEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String,
    @Column(name = "state", columnDefinition = "BOOL")
    var state: Boolean = false,
    @Column(name = "created_at",columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "franchisor_id", columnDefinition = "CHAR(16)")
    val franchisorId: FranchisorEntity
)
