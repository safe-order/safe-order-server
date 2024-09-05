package kr.yz.safeorder.domain.product

import jakarta.persistence.*
import kr.yz.safeorder.domain.supplier.SupplierEntity

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String,
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)") // 이름
    val name: String,
    @Column(name = "retail_price", nullable = false) // 소매가
    val retailPrice: String,
    @Column(name = "wholesale_price", nullable = false) // 도매가
    val wholesalePrice: String,
    @Column(name = "explanation", nullable = false, columnDefinition = "VARCHAR(500)") // 설명
    val explanation: String,
    @Column(name = "tax_free_state", nullable = false, columnDefinition = "BOOL") // 세금 면제
    val taxFreeState: Boolean,
    @Column(name = "sale_state", nullable = false, columnDefinition = "BOOL") // 판매 상태
    val saleState: Boolean,
    @Column(name = "image_url", nullable = false) // 사진 URL
    val imageUrl: String,
    @Column(name = "tex", nullable = false) // 사진 URL
    val tex: Int,
    @OneToOne
    @JoinColumn(name = "supplier_id", columnDefinition = "CHAR(16)")
    val supplierId: SupplierEntity
)
