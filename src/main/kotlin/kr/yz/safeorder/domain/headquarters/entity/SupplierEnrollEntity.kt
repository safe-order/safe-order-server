package kr.yz.safeorder.domain.headquarters.entity

import jakarta.persistence.*
import kr.yz.safeorder.domain.supplier.SupplierEntity

@Entity
@Table(name = "supplier_enroll")
data class SupplierEnrollEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(columnDefinition = "BOOL")
    val state: Boolean = false,
    @Column(columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "headquarters_id")
    val headquartersId: HeadquartersEntity,
    @OneToOne
    @JoinColumn(name = "supplier_id")
    val supplierId: SupplierEntity
)
