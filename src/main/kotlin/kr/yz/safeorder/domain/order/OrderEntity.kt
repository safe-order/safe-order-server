package kr.yz.safeorder.domain.order

import jakarta.persistence.*

@Entity
@Table(name = "`order`")
data class OrderEntity(
    @Id
    val id: String,
    @Column(name = "month", nullable = false)
    val month: String,
    @Column(name = "date_time", nullable = false)
    val dateTime: String,
    @Column(name = "lot_number", nullable = false)
    val lotNumber: String,
    @Column(name = "amount", nullable = false)
    val amount: String,
    @Column(name = "price", nullable = false)
    val price: String,
    @Column(name = "sales", nullable = false)
    val sales: String,
    @Column(name = "tax", nullable = false)
    val tax: String,
    @Column(name = "customer_id", nullable = false)
    val customerId: String,
    @Column(name = "customer_name", nullable = false)
    val customerName: String,
    @Column(name = "release_date", nullable = false)
    val releaseDate: String,
    @Column(name = "invoice_number", nullable = false)
    val invoiceNumber: String,
    @Column(name = "etc", nullable = false)
    val etc: String
)