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

        return UserResponse(usuario.username, usuario.email, usuario.pedidosRealizados, usuario.informacionEnFactura)
    }

    fun loginUser(userEmail: String, userPassword: String): UserResponse? {
        val user: Usuario? = this.usuarioRepository.findByEmail(userEmail)
        if (user == null){
            return null
        }else if(this.passwordEncoder.isPassword(userPassword, user!!.contraseña())) {
            return UserResponse(user.username, user.email, user.pedidosRealizados, user.informacionEnFactura)
        }
        return null
    }

    fun getByUsername(userName: String): UserResponse? {
        val user =  this.usuarioRepository.findById(userName)
        return if(user.isEmpty){
            null
        }else{
            val usuario = user.get()
                UserResponse(usuario.username,
                            usuario.email,
                            usuario.pedidosRealizados,
                            usuario.informacionEnFactura)
        }
    }

    fun guardarFactura(facturaWrapper: FacturaWrapper): UserResponse? {
        val user = this.usuarioRepository.findById(facturaWrapper.nombreUsuario)
        return if(user.isEmpty){
            null
        }else{
            val usuario = user.get()
            this.realizarPedido(usuario, facturaWrapper.productos)
            this.usuarioRepository.save(usuario)
            UserResponse(usuario.username, usuario.email, usuario.pedidosRealizados, usuario.informacionEnFactura)
        }
    }

    private fun realizarPedido(user: Usuario, pedidos: Set<ProductoCantidad>) {
        pedidos.forEach{unPedido -> user.realizarPedido(unPedido.producto, unPedido.id, unPedido.cantidad, unPedido.precio) }
        pedidos.forEach{unPedido -> this.productoService.decrementarStock(unPedido.producto, unPedido.cantidad)}
    }

    fun pedidosDe(email: String): MutableSet<Pedido> {
       return this.usuarioRepository.findByEmail(email)!!.pedidosQueRealizo()
    }

    fun checkEmail(userEmailRegister: String): Int {
        return this.usuarioRepository.checkEmail(userEmailRegister)
    }

    fun guardarInfoFacturaDeUsuario(usuarioFacturaInfoWrapper: UsuarioFacturaInfoWrapper): UserResponse?{
        val usuario = this.usuarioRepository.findById(usuarioFacturaInfoWrapper.username)
        return if(usuario.isEmpty){
            return null
        }else{
            val usuario = usuario.get()
            usuario.generarInfoFactura(usuarioFacturaInfoWrapper.usuarioFacturaInfo)
            this.usuarioRepository.save(usuario)
            return UserResponse(usuario.username, usuario.email, usuario.pedidosRealizados, usuario.informacionEnFactura)
        }
    }

}