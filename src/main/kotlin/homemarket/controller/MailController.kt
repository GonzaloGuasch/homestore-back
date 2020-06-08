package homemarket.controller

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Mail")
class MailController(private val javaMailSender: JavaMailSender) {

    @GetMapping("/{para}/{precio}/{nombre}")
    fun sendEmail(@PathVariable para: String,
                  @PathVariable precio: String,
                  @PathVariable nombre: String){
        val msg = SimpleMailMessage()
        msg.setTo(para)
        msg.setSubject("Gracias " + nombre.toLowerCase().capitalize() + " por tu compra")
        msg.setText("Buenas!" + "\n" + "Gracias por comprar en Home Store Market!" +
                    "\n" + "Dentro de 48 horas te llega tu pedido!" +
                    "\n" + "Saludos desde el equipo de soyTuMarket.")

        javaMailSender.send(msg)
    }
}


