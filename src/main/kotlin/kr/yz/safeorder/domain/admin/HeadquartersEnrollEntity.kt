package kr.yz.safeorder.domain.admin

import jakarta.persistence.*
import kr.yz.safeorder.domain.headquarters.entity.HeadquartersEntity

@Entity
@Table(name = "headquarters_enroll")
data class HeadquartersEnrollEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "state", columnDefinition = "BOOL")
    var state: Boolean = false,
    @Column(name = "created_at",columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "headquarters_id")
    val headquartersId: HeadquartersEntity
)
