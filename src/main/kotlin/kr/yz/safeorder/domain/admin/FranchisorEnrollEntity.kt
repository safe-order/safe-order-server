package kr.yz.safeorder.domain.admin

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchisor.FranchisorEntity

@Entity
@Table(name = "franchisor_enroll")
data class FranchisorEnrollEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "state", columnDefinition = "BOOL")
    var state: Boolean = false,
    @Column(name = "created_at",columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "franchisor_id")
    val franchisorId: FranchisorEntity
)
