package kr.yz.safeorder.domain.user.controller.dto

import kr.yz.safeorder.domain.user.UserEntity
import kr.yz.safeorder.domain.user.type.UserType

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
    val back: String, // 은행
    val bankNumber: String, // 계좌번호
    val userType: UserType // 유저 타입
)


data class UserSignupRequestDto(
    val id: String, // 아이디
    var password: String, // 비밀번호
    val companyName: String, // 상호 or 본사 이름
    val representativName: String? = null, // 대표자 이름
    val representativPhone: String? = null, // 대표자 전화번호
    val managerPhone: String? = null, // 담당자 전화번호
    val brandName: String? = null, // 브랜드 이름
    val address: String, // 주소
    val detailedAddress: String, // 상세 주소
    val businessNumber: String, // 사업자 번호
    val businessRegistrationUrl: String, // 사업자 등록증 URL
    val back: String, // 은행
    val bankNumber: String, // 계좌번호
    val userType: UserType // 유저 타입
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
        back = this.back,
        bankNumber = this.bankNumber,
        userType = this.userType
    )
}