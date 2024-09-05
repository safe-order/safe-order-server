package kr.yz.safeorder.domain.franchisor

import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

// 본사
@Entity
@Table(name = "franchisor")
data class FranchisorEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String, // 아이디
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(12)")
    val username: String, // 유저 아이디
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(50)")
    var password: String, // 비밀번호
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    val createdAt: LocalDateTime, // 만든 날짜
    @Column(name = "address", nullable = false, unique = true)
    var address: String, // 주소
    @Column(name = "company_name", nullable = false, unique = true)
    var companyName: String, // 회사 이름
    @Column(name = "detailed_address", nullable = false, unique = true)
    var detailedAddress: String, // 상세 주소
    @Column(name = "representative_phone", unique = true, columnDefinition = "VARCHAR(11)")
    val representativePhone: String, // 대표자 전화번호
    @Column(name = "business_number", nullable = false, unique = true, columnDefinition = "CHAR(10)")
    var businessNumber: String, // 사업자 번호
    @Column(name = "business_registration_url", nullable = false, unique = true)
    val businessRegistrationUrl: String, // 사업자 등록증 URL
) {
    fun hashPassword(passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(this.password)
    }
}
