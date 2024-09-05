package kr.yz.safeorder.domain.shopping_cart

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchise.FranchiseEntity
import kr.yz.safeorder.domain.product.ProductEntity

@Entity
@Table(name = "shopping_cart")
data class ShoppingCartEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String,
    @OneToOne
    @JoinColumn(name = "product_id", columnDefinition = "CHAR(16)")
    val product: ProductEntity,
    @OneToOne
    @JoinColumn(name = "franchise", columnDefinition = "CHAR(16)")
    val franchise: FranchiseEntity
)
