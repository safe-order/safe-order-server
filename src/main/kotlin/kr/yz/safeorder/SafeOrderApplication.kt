package kr.yz.safeorder

import kr.yz.safeorder.global.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class SafeOrderApplication

fun main(args: Array<String>) {
    runApplication<SafeOrderApplication>(*args)
}
