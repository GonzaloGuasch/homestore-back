package homemarket.controller.mailSender

import homemarket.model.ListaProducosDecrementar
import homemarket.model.ProductoCantidad
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/Mail")
@CrossOrigin(origins = ["http://localhost:3000"])
class MailController(private val javaMailSender: JavaMailSender) {

    @PostMapping("/EmailGuest/{para}/{nombre}")
    fun sendEmailGuest( @PathVariable para: String,
                        @PathVariable nombre: String,
                        @RequestBody productos: ListaProducosDecrementar): String {
        val msg = this.javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(msg, true)
        val productos = this.generarDivsProductos(productos.productos)

        helper.setTo(para)
        helper.setSubject("Gracias " + nombre.toLowerCase().capitalize() + " por tu compra")

        helper.setText("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<style>\n" +
                "\t\t#id{\n" +
                "\t\t\tmargin-left: 30%;\n" +
                "\t\t\tmargin-top: 60px;\n" +
                "\t\t\tcolor: #7b7b7b;\n" +
                "\t\t\tfont-weight: 500;\n" +
                "\t\t\ttext-transform: uppercase;\n" +
                "\t\t\tbackground-color: white;\n" +
                "\t\t\twidth: 40%;\n" +
                "\n" +
                "\t\t\tpadding: 50px 0 30px 65px;\n" +
                "\t\t}\n" +
                "\t\tbody{\n" +
                "\t\t\tbackground-color: #eeeeee;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<div id=\"id\">\n" +
                "\t\t<div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\tGracias por comprar en Home Store Market!\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\tSu compra llegara dentro de 48 horas habiles.\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\tRECUERDE: <br />\n" +
                "\t\t\t\tse puede registrar en la pestaña 'Unirse/Ingresar' <br />\n" +
                "\t\t\t\t* Tener la lista de todos los pedidas ya realizados y poder repetirlos\n" +
                "\t\t\t\t* Registrar su información de la factura para llenarla una sola vez\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\tTu compra: \n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\t$productos\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t\tSaludos desde el equipo de SoyTuMarket!\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>", true)

        javaMailSender.send(msg)

        return "email enviado"
    }

    @PostMapping("/EmailUsuario/{para}/{precio}/{nombre}")
    fun sendEmailUser(
            @PathVariable para: String,
            @PathVariable nombre: String,
            @RequestBody productos: ListaProducosDecrementar): String {
        val msg = this.javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(msg, true)
        val productos = this.generarDivsProductos(productos.productos)
        helper.setTo(para)
        helper.setSubject("Gracias " + nombre.toLowerCase().capitalize() + " por tu compra")

        helper.setText(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<style>\n" +
                        "\t\t#id{\n" +
                        "\t\t\tmargin-left: 30%;\n" +
                        "\t\t\tmargin-top: 60px;\n" +
                        "\t\t\tcolor: #7b7b7b;\n" +
                        "\t\t\tfont-weight: 500;\n" +
                        "\t\t\ttext-transform: uppercase;\n" +
                        "\t\t\tbackground-color: white;\n" +
                        "\t\t\twidth: 40%;\n" +
                        "\n" +
                        "\t\t\tpadding: 50px 0 30px 65px;\n" +
                        "\t\t}\n" +
                        "\t\tbody{\n" +
                        "\t\t\tbackground-color: #eeeeee;\n" +
                        "\t\t}\n" +
                        "\t</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\t<div id=\"id\">\n" +
                        "\t\t<div>\n" +
                        "\t\t\t<div>\n" +
                        "\t\t\t\tGracias por comprar en Home Store Market!\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div>\n" +
                        "\t\t\t\tSu compra llegara dentro de 48 horas habiles.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div>\n" +
                        "\t\t\t\tTu compra: \n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div>\n" +
                        "\t\t\t\t$productos\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div>\n" +
                        "\t\t\t\tSaludos desde el equipo de SoyTuMarket!\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>\n" +
                        "</html>", true)

        javaMailSender.send(msg)

        return "Email enviado"
    }

    private fun generarDivsProductos(productos: Array<ProductoCantidad>): String {
        var divs = ""
        productos.forEach { divs = divs + it.producto + " | " + " cantidad: " + it.cantidad.toString() + "<br/>" }
        return divs
    }

}


