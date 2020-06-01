package homemarket.service

import homemarket.Repositories.UsuarioRepository
import homemarket.controller.UsuarioController
import homemarket.model.PasswordEncoder
import homemarket.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val passwordEncoder: PasswordEncoder,
                     private val usuarioRepository: UsuarioRepository){

    fun saveUser(usuario: Usuario): Usuario {
        val plain_password: String = usuario.contraseña()
        val encode_password = this.passwordEncoder.encode(plain_password)
        usuario.setEncodePassword(encode_password)

        return this.usuarioRepository.save(usuario)
    }

    fun loginUser(userEmail: String, userPassword: String): Boolean {
        var user: Usuario = this.usuarioRepository.findByEmail(userEmail)!!
        return this.passwordEncoder.isPassword(userPassword, user.contraseña())
    }
}