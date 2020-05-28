package homemarket.controller

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Mail")
class MailController(private val javaMailSender: JavaMailSender) {

    @GetMapping("/")
    fun sendEmail(){//@RequestBody emailWrapper: EmailWrapper){
        val msg = SimpleMailMessage()
        msg.setTo("gonzaloguasch@outlook.com")
        msg.setSubject("Probando el mail con spring")
        msg.setText("Aguante charly garcia vieja")

        javaMailSender.send(msg)
    }
}


