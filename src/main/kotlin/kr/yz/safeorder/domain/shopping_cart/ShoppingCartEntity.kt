package kr.yz.safeorder.domain.shopping_cart

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchise.FranchiseEntity
import kr.yz.safeorder.domain.product.ProductEntity

@Entity
@Table(name = "shopping_cart")
data class ShoppingCartEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @OneToOne
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
    @OneToOne
    @JoinColumn(name = "franchise")
    val franchise: FranchiseEntity
)
