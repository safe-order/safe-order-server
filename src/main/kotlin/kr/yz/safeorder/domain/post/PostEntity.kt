package kr.yz.safeorder.domain.post

import jakarta.persistence.*

@Entity
@Table(name = "post")
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "title", columnDefinition = "VARCHAR(30)")
    val title: String,
    @Column(name = "detail", columnDefinition = "VARCHAR(500)")
    val detail: String,
    @Column(name = "created_at", columnDefinition = "DATETIME")
    val createdAt: String,
    @Column(name = "hits")
    val hits: Int = 0,
    @Column(name = "type",columnDefinition = "VARCHAR(10)")
    val type: String,
    @Column(name = "username",columnDefinition = "CHAR(16)")
    val username: String
)