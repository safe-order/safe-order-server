package kr.yz.safeorder.domain.franchisor

import jakarta.persistence.*
import kr.yz.safeorder.domain.supplier.SupplierEntity

@Entity
@Table(name = "supplier_enroll")
data class SupplierEnrollEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String,
    @Column(columnDefinition = "BOOL")
    val state: Boolean = false,
    @Column(columnDefinition = "DATETIME")
    val createdAt: String,
    @OneToOne
    @JoinColumn(name = "franchisor_id", columnDefinition = "CHAR(16)")
    val franchisorId: FranchisorEntity,
    @OneToOne
    @JoinColumn(name = "supplier_id", columnDefinition = "CHAR(16)")
    val supplierId: SupplierEntity
)
