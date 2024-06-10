package kr.yz.safeorder

import io.viascom.nanoid.NanoId
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SafeOrderApplicationTests {

    @Test
    fun contextLoads() {
        val id = NanoId.generate(100)
        println(id)
    }

}
