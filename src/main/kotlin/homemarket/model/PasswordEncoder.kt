package homemarket.model


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {

    private val bcrypt = BCryptPasswordEncoder()
    fun encode(passwordToEncode: String): String{

        return this.bcrypt.encode((passwordToEncode))
    }

    fun isPassword(userInputPassword:String, crypthedPassword: String): Boolean{
        return this.bcrypt.matches(userInputPassword, crypthedPassword)
    }
}