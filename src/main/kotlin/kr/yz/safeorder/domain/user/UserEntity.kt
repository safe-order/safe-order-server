package kr.yz.safeorder.domain.user

import jakarta.persistence.*
import kr.yz.safeorder.domain.user.type.UserType

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    val code: String,
    @Column(name = "id", unique = true, nullable = false)
    val id: String, // 아이디
    @Column(name = "password", nullable = false)
    var password: String, // 비밀번호
    @Column(name = "created_at", nullable = false)
    val createdAt: String,
    @Column(name = "company_name", nullable = false)
    val companyName: String, // 상호 or 본사 이름
    @Column(name = "representativ_name", nullable = true)
    val representativName: String? = null, // 대표자 이름
    @Column(name = "representativ_phone", nullable = true)
    val representativPhone: String? = null, // 대표자 전화번호
    @Column(name = "manager_phone", nullable = true)
    val managerPhone: String? = null, // 담당자 전화번호
    @Column(name = "brandName", nullable = true)
    val brandName: String? = null, // 브랜드 이름
    @Column(name = "address", nullable = false, unique = true)
    val address: String, // 주소
    @Column(name = "detailed_address", nullable = false, unique = true)
    val detailedAddress: String, // 상세 주소
    @Column(name = "business_number", nullable = false, unique = true)
    val businessNumber: String, // 사업자 번호
    @Column(name ="business_registration_url", nullable = false, unique = true)
    val businessRegistrationUrl: String, // 사업자 등록증 URL
    @Column(name = "bank", nullable = false)
    val bank: String, // 은행
    @Column(name = "bank_number", nullable = false)
    val bankNumber: String, // 계좌번호
    @Column(name = "user_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    val userType: UserType // 유저 타입
)
