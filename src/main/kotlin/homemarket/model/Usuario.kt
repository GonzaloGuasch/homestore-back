package homemarket.model

import javax.persistence.*


@Entity
class Usuario(@Id
              var username: String,
              var email: String,
              var password: String,
              @OneToMany(cascade = [CascadeType.ALL])
              var pedidosRealizados: MutableSet<Pedido> =  mutableSetOf(),
              @OneToOne(cascade = [CascadeType.ALL])
              var informacionEnFactura: UsuarioFacturaInfo? = null){

    fun nombreDeUsuario(): String{
        return this.username
    }

    fun emailUsuario(): String {
        return this.email
    }

    fun contraseña(): String {
        return this.password
    }

    fun pedidosQueRealizo(): MutableSet<Pedido> {
        return this.pedidosRealizados
    }

    fun setEncodePassword(encodePassword: String) {
        this.password = encodePassword
    }

    fun realizarPedido(nombreProducto: String, id: Int, cantidad: Int, precio: Int) {

        this.pedidosRealizados.add(Pedido(nombreProducto, cantidad, precio, id))
    }

    fun generarInfoFactura(usuarioFacturaInfo: UsuarioFacturaInfo) {
        this.informacionEnFactura = usuarioFacturaInfo
    }

}
