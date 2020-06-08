package homemarket.controller

import com.google.gson.GsonBuilder
import com.mercadopago.exceptions.MPException
import com.mercadopago.resources.MerchantOrder
import com.mercadopago.resources.Payment
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.*
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/MP")
class MercadoPagoController {

    @GetMapping("PagoDeProducto")
    fun pagoDeProducto(): String? {
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

        preferencia.payer = payer
        preferencia.binaryMode = true
        val result = preferencia.save()

        return result.sandboxInitPoint
    }

    @GetMapping("/success")
    @Throws(MPException::class)
    fun success(request: HttpServletRequest?,
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
        val payment = Payment.findById(collectionId)
        println(GsonBuilder().setPrettyPrinting().create().toJson(payment))
        model.addAttribute("payment", payment)
        return "ok"
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
        val order = MerchantOrder.findById(merchantOrderId)
        val payment = Payment.findById(collectionId)
        println(GsonBuilder().setPrettyPrinting().create().toJson(order))
        println(GsonBuilder().setPrettyPrinting().create().toJson(payment))
        model.addAttribute("items", preference.items)
        model.addAttribute("payment", payment)
        return "failure"
    }

}