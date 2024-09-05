package kr.yz.safeorder.domain.supplier

import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "supplier")
data class SupplierEntity(
    @Id
    @Column(columnDefinition = "CHAR(16)")
    val id: String, // 아이디
    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(12)")
    val username: String, // 유저 아이디
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(50)")
    var password: String, // 비밀번호
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
    val createdAt: String, // 만든 날짜
    @Column(name = "company_name", nullable = false, unique = true)
    var companyName: String, // 상호
    @Column(name = "representative_name", nullable = false)
    val representativeName: String, // 대표자 이름
    @Column(name = "representative_phone", nullable = false, unique = true)
    val representativePhone: String, // 대표자 전화번호
    @Column(name = "manager_phone", nullable = false, unique = true)
    val managerPhone: String, // 담당자 전화번호
    @Column(name = "address", nullable = false, unique = true)
    var address: String, // 주소
    @Column(name = "detailed_address", nullable = false, unique = true)
    var detailedAddress: String, // 상세 주소
    @Column(name = "business_number", nullable = false, unique = true)
    var businessNumber: String, // 사업자 번호
    @Column(name = "business_registration_url", nullable = false, unique = true)
    val businessRegistrationUrl: String, // 사업자 등록증 URL
    @Column(name = "bank", nullable = false)
    var bank: String, // 은행
    @Column(name = "bank_book_name", nullable = false)
    var bankBookName: String, // 통장 이름
    @Column(name = "bank_number", nullable = false, unique = true)
    var bankNumber: String, // 계좌번호
    @Column(name = "bank_url", nullable = false, unique = true)
    val bankUrl: String // 통장 사본 URL
) {
    fun hashPassword(passwordEncoder: PasswordEncoder) {
        this.password = passwordEncoder.encode(this.password)
    }
}