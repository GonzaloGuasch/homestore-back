package homemarket.service

import homemarket.Repositories.UsuarioRepository
import homemarket.model.*
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val passwordEncoder: PasswordEncoder,
                     private val usuarioRepository: UsuarioRepository,
                     private val productoService: ProductoService){

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

    fun guardarFactura(facturaWrapper: FacturaWrapper): UserResponse{
        val user: Usuario = this.usuarioRepository.findById(facturaWrapper.nombreUsuario).get()
        this.realizarPedido(user, facturaWrapper.productos)
        this.usuarioRepository.save(user)

        return UserResponse(user.username, user.email, user.pedidosRealizados)
    }

    private fun realizarPedido(user: Usuario, pedidos: Set<ProductoCantidad>) {
        pedidos.forEach{unPedido -> user.realizarPedido(unPedido.nombre, unPedido.cantidad) }
        pedidos.forEach{unPedido -> this.productoService.decrementarStock(unPedido.nombre, unPedido.cantidad)}
    }

    fun pedidosDe(username: String): MutableSet<Pedido> {
       return this.usuarioRepository.findById(username).get().pedidosQueRealizo()
    }

    fun checkEmail(userEmailRegister: String): Int {
        return this.usuarioRepository.checkEmail(userEmailRegister)
    }

}