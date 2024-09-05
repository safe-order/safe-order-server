package kr.yz.safeorder.domain.certification.service

import io.viascom.nanoid.NanoId
import kr.yz.safeorder.domain.certification.BusinessNumberTokenEntity
import kr.yz.safeorder.domain.certification.dto.response.CheckValidVerifyCodeResponseDto
import kr.yz.safeorder.domain.certification.repository.BusinessNumberTokenRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class CertificationService(
    private val businessNumberTokenRepository: BusinessNumberTokenRepository,
    private val webClient: WebClient,
    @Value("\${api-key.nts-businessman}") private val ntsBusinessmanApiKey: String,
) {

    fun checkValidBusinessNumber(businessNumber: String): CheckValidVerifyCodeResponseDto {
        val requestBody: MutableMap<String, List<String>> = mutableMapOf()
        requestBody["b_no"] = listOf(businessNumber)
        webClient.post()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/status")
                    .queryParam("serviceKey", ntsBusinessmanApiKey)
                    .build()
            }
            .bodyValue(requestBody)
            .retrieve()
            .onStatus({ responseStatus -> responseStatus != HttpStatus.OK }){
                throw TODO("존재하지 않는 사업자번호")
            }
            .toBodilessEntity()
            .subscribe()

        val token = createToken()
        val tokenCodeData = BusinessNumberTokenEntity(businessNumber, token)
        businessNumberTokenRepository.save(tokenCodeData)
        return CheckValidVerifyCodeResponseDto(token)
    }

    fun checkValidBusinessNumberToken(token: String, businessNumber: String) {
        if(!businessNumberTokenRepository.existsByBusinessNumberAndToken(businessNumber, token)){
            throw TODO("존재 하지 않는 Token")
        }
    }

    private fun createToken(): String {
        return NanoId.generate(12)
    }

}