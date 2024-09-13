package kr.yz.safeorder.domain.admin

import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "admin")
data class AdminEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, // 아이디
    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(12)")
    val username: String, // 유저 아이디
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(50)")
    var password: String // 비밀번호
) {
    fun hashPassword(passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(this.password)
    }
}
