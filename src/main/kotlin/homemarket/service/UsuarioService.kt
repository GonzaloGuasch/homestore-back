package homemarket.service

import homemarket.Repositories.UsuarioRepository
import homemarket.model.PasswordEncoder
import homemarket.model.ProductoCantidad
import homemarket.model.UserResponse
import homemarket.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val passwordEncoder: PasswordEncoder,
                     private val usuarioRepository: UsuarioRepository){

    fun saveUser(usuario: Usuario): UserResponse {
        val plain_password: String = usuario.contraseña()
        val encode_password = this.passwordEncoder.encode(plain_password)
        usuario.setEncodePassword(encode_password)
        this.usuarioRepository.save(usuario)

        return UserResponse(usuario.username, usuario.email, usuario.pedidosRealizados)
    }

    fun loginUser(userEmail: String, userPassword: String): UserResponse? {
        val user: Usuario? = this.usuarioRepository.findByEmail(userEmail)
        if (user == null){
            return null
        }else if(this.passwordEncoder.isPassword(userPassword, user!!.contraseña())) {
            return UserResponse(user.username, user.email, user.pedidosRealizados)
        }
        return null
    }

    fun getByUsername(userName: String): Usuario {
        return this.usuarioRepository.findById(userName).get()
    }

    fun guardarFactura(productoConCantidad: ProductoCantidad, username: String): Any {
        val user: Usuario = this.usuarioRepository.findById(username).get()
        //user.agregarPedido()
        return 0
    }

}