package homemarket.controller

import com.mercadopago.MercadoPago
import com.mercadopago.exceptions.MPException
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.*
import homemarket.model.*
import homemarket.service.ProductoService
import homemarket.service.UsuarioService
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView


@RestController
@RequestMapping("/MP")
@CrossOrigin(origins = ["http://localhost:3000"])
class MercadoPagoController(private var usuarioService: UsuarioService,
                            private var productoService: ProductoService){

    @PostMapping("PagoDeProducto")
    fun pagoDeProducto(@RequestBody facturaWrapper: FacturaWrapper): String? {
        MercadoPago.SDK.configure("Cliente Secret", "Cliente ID")
        MercadoPago.SDK.setAccessToken("TEST-1388689821315510-052714-dddc281a34bbae27c432c8b87e55bb10-178004482")
        val preferencia = Preference()
        preferencia.setBackUrls(BackUrls()
                .setFailure("http://localhost:8080/MP/redirect")
                .setSuccess("http://localhost:8080/MP/redirectSuccess"))

        facturaWrapper.productos.forEach {var i = Item().setTitle(it.producto)
                                                 .setQuantity(it.cantidad)
                                                 .setUnitPrice(it.precio.toFloat()  )
                                            preferencia.appendItem(i)}

        val payer = Payer()
        payer.setName(facturaWrapper.nombreUsuario)

        preferencia.autoReturn = Preference.AutoReturn.all

        preferencia.payer = payer
        preferencia.binaryMode = true
        val result = preferencia.save()

        return result.sandboxInitPoint
    }




    @GetMapping("/redirectSuccess")
    @Throws(MPException::class)
    fun redirectSuccess(): RedirectView {
        return RedirectView("http://localhost:3000/CompraFinalizada")
    }

    @GetMapping("/redirect")
    @Throws(MPException::class)
    fun success(): RedirectView {
        return RedirectView("http://localhost:3000/")
    }

}