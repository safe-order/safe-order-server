package kr.yz.safeorder.domain.product

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    val id: String,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "price", nullable = false)
    val price: String,
    @Column(name = "explanation", nullable = false)
    val explanation: String,
    @Column(name = "tax_free_state", nullable = false)
    val taxFreeState: Boolean,
    @Column(name ="sale_tate",nullable = false)
    val saleState: Boolean
)
