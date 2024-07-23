package kr.yz.safeorder.domain.user.controller.dto

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.user.UserEntity
import kr.yz.safeorder.domain.user.type.UserType
import java.time.LocalDateTime

// 로그인
data class UserLoginResponseDto(
    val id: String, // 아이디
    val companyName: String, // 상호 or 본사 이름
    val representativName: String?, // 대표자 이름
    val representativPhone: String?, // 대표자 전화번호
    val managerPhone: String?, // 담당자 전화번호
    val brandName: String?, // 브랜드 이름
    val address: String, // 주소
    val detailedAddress: String, // 상세 주소
    val businessNumber: String, // 사업자 번호
    val businessRegistrationUrl: String, // 사업자 등록증 URL
    val bank: String, // 은행
    val bankNumber: String, // 계좌번호
    val userType: UserType // 유저 타입
)

// 회원가입
data class UserSignupRequestDto(
    val id: String, // 아이디
    var password: String, // 비밀번호
    val createdAt: String = LocalDateTime.now().toString(),
    val companyName: String, // 상호 or 본사 이름
    val representativName: String? = null, // 대표자 이름
    val representativPhone: String? = null, // 대표자 전화번호
    val managerPhone: String? = null, // 담당자 전화번호
    val brandName: String? = null, // 브랜드 이름
    val address: String, // 주소
    val detailedAddress: String, // 상세 주소
    val businessNumber: String, // 사업자 번호
    val businessRegistrationUrl: String, // 사업자 등록증 URL
    val bank: String, // 은행
    val bankNumber: String, // 계좌번호
    val userType: UserType // 유저 타입
)

// 유저 자세한 정보
data class UserProfileDetailDto(
    val id: String, // 아이디
    val companyName: String, // 상호 or 본사 이름
    val representativName: String?, // 대표자 이름
    val representativPhone: String?, // 대표자 전화번호
    val managerPhone: String?, // 담당자 전화번호
    val brandName: String?, // 브랜드 이름
    val address: String, // 주소
    val businessNumber: String, // 사업자 번호
    val detailedAddress: String, // 상세 주소
    val bank: String, // 은행
    val bankNumber: String, // 계좌번호
)

fun UserEntity.toUserLoginResponseDto(): UserLoginResponseDto {
    return UserLoginResponseDto(
        id = this.id,
        companyName = this.companyName,
        representativName = this.representativName,
        representativPhone = this.representativPhone,
        managerPhone = this.managerPhone,
        brandName = this.brandName,
        address = this.address,
        detailedAddress = this.detailedAddress,
        businessNumber = this.businessNumber,
        businessRegistrationUrl = this.businessRegistrationUrl,
        bank = this.bank,
        bankNumber = this.bankNumber,
        userType = this.userType
    )
}

fun UserSignupRequestDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        password = this.password,
        createdAt = this.createdAt,
        companyName = this.companyName,
        representativName = this.representativName,
        representativPhone = this.representativPhone,
        managerPhone = this.managerPhone,
        brandName = this.brandName,
        address = this.address,
        detailedAddress = this.detailedAddress,
        businessNumber = this.businessNumber,
        businessRegistrationUrl = this.businessRegistrationUrl,
        bank = this.bank,
        bankNumber = this.bankNumber,
        userType = this.userType,
        code = NanoId.generate(12, "1234567890")
    )
}

fun UserEntity.toUserProfileDetailResponseDto(): UserProfileDetailDto = UserProfileDetailDto(
    id = this.id,
    companyName = this.companyName,
    representativName = this.representativName,
    representativPhone = this.representativPhone,
    managerPhone = this.managerPhone,
    brandName = this.brandName,
    address = this.address,
    detailedAddress = this.detailedAddress,
    bank = this.bank,
    bankNumber = this.bankNumber,
    businessNumber = this.businessNumber
)