package kr.yz.safeorder.global.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import io.netty.resolver.DefaultAddressResolverGroup
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.*
import org.springframework.web.util.DefaultUriBuilderFactory
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration


@Configuration
class WebClientConfig {
    private val logger: Logger = LoggerFactory.getLogger(WebClientConfig::class.java)

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        val factory = DefaultUriBuilderFactory("https://api.odcloud.kr/api/nts-businessman/v1")
        factory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE

        val httpClient: HttpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofSeconds(1))
            .doOnConnected { conn ->
                conn
                    .addHandlerLast(ReadTimeoutHandler(5))
                    .addHandlerLast(WriteTimeoutHandler(5))
            }.resolver(DefaultAddressResolverGroup.INSTANCE)


        return builder
            .filters {
                it.add(logRequest())
                it.add(logResponse())
            }
            .baseUrl("https://api.odcloud.kr/api/nts-businessman/v1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .uriBuilderFactory(factory)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
            logger.info("Request: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.body())
            Mono.just(clientRequest)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
            logger.info("Response: {} {} {}", clientResponse.request().method, clientResponse.statusCode(), clientResponse.request().uri)
            Mono.just(clientResponse)
        }
    }
}