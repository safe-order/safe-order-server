package kr.yz.safeorder.domain.order

import jakarta.persistence.*
import kr.yz.safeorder.domain.franchise.FranchiseEntity
import kr.yz.safeorder.domain.headquarters.entity.HeadquartersEntity
import kr.yz.safeorder.domain.order.type.OrderState
import kr.yz.safeorder.domain.product.ProductEntity

@Entity
@Table(name = "`order`")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "date_time", nullable = false, columnDefinition = "DATETIME") // 주문 날짜
    val dateTime: String,
    @Column(name = "amount", nullable = false) // 수량
    val amount: String,
    @Column(name = "price", nullable = false) // 가격
    val price: String,
    @Column(name = "invoice_number", nullable = false, columnDefinition = "VARCHAR(13)") // 송장 번호
    val invoiceNumber: String,
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_state")
    val orderState: OrderState,
    @OneToOne
    @JoinColumn(name = "product_id")
    val productId: ProductEntity, // 제품
    @OneToOne
    @JoinColumn(name = "franchise_id")
    val franchiseId: FranchiseEntity, // 가맹점
    @OneToOne
    @JoinColumn(name = "headquarters_id")
    val headquartersId: HeadquartersEntity // 본사
)