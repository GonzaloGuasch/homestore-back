package homemarket

import com.mercadopago.MercadoPago
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class HomeStoreMarketApplication

fun main(args: Array<String>) {
	runApplication<HomeStoreMarketApplication>(*args)

}

@Throws(Exception::class)
fun run(vararg args: String?) {
	MercadoPago.SDK.configure("Cliente Secret", "Cliente ID")
	MercadoPago.SDK.setAccessToken("TEST-1388689821315510-052714-dddc281a34bbae27c432c8b87e55bb10-178004482")

}
