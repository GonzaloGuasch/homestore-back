package homemarket.controller

import com.google.gson.GsonBuilder
import com.mercadopago.MercadoPago
import com.mercadopago.exceptions.MPException
import com.mercadopago.resources.MerchantOrder
import com.mercadopago.resources.Payment
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.*
import homemarket.model.FacturaWrapper
import homemarket.model.ListaProductosWrapper
import homemarket.model.ListaWrapper
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/MP")
@CrossOrigin(origins = ["http://localhost:3000"])
class MercadoPagoController {

    @PostMapping("PagoDeProducto")
    fun pagoDeProducto(@RequestBody facturaWrapper: FacturaWrapper): String? {
        MercadoPago.SDK.configure("Cliente Secret", "Cliente ID")
        MercadoPago.SDK.setAccessToken("TEST-1388689821315510-052714-dddc281a34bbae27c432c8b87e55bb10-178004482")
        val preferencia = Preference()
        preferencia.setBackUrls(BackUrls()
                .setFailure("http://localhost:8080/MP/failure")
                .setPending("http://localhost:8080/MP/pending")
                .setSuccess("http://localhost:8080/MP/success"))
        val item = Item()
        item.setTitle("Mi producto")
                .setQuantity(1)
                .setUnitPrice(75.56.toFloat())

        val payer = Payer()
        payer.setName("Charles")
                .setSurname("Luevano")
                .setEmail("charles@hotmail.com")
                .setDateCreated("2018-06-02T12:58:41.425-04:00")
                .setPhone(Phone()
                        .setAreaCode("")
                        .setNumber("949 128 866"))
                .setIdentification(Identification()
                        .setType("DNI")
                        .setNumber("12345678"))
                .setAddress(Address()
                        .setStreetName("Cuesta Miguel Armendáriz")
                        .setStreetNumber(1004)
                        .setZipCode("11020"))

        preferencia.appendItem(item)
        preferencia.autoReturn = Preference.AutoReturn.all

        preferencia.payer = payer
        preferencia.binaryMode = true
        val result = preferencia.save()

        return result.sandboxInitPoint
    }

    @GetMapping("/success")
    @Throws(MPException::class)
    fun success(): RedirectView {
        return RedirectView("http://localhost:3000/")
    }


    @GetMapping("/failure")
    @Throws(MPException::class)
    fun failure(request: HttpServletRequest?,
                @RequestParam("collection_id") collectionId: String?,
                @RequestParam("collection_status") collectionStatus: String?,
                @RequestParam("external_reference") externalReference: String?,
                @RequestParam("payment_type") paymentType: String?,
                @RequestParam("merchant_order_id") merchantOrderId: String?,
                @RequestParam("preference_id") preferenceId: String?,
                @RequestParam("site_id") siteId: String?,
                @RequestParam("processing_mode") processingMode: String?,
                @RequestParam("merchant_account_id") merchantAccountId: String?,
                model: Model
    ): String? {
        model.addAttribute("preference_id", preferenceId)
        val preference = Preference.findById(preferenceId)
        val payment = Payment.findById(collectionId)
        model.addAttribute("items", preference.items)
        model.addAttribute("payment", payment)
        return "failure"
    }

}