package homemarket.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne


@Entity
class Usuario(@Id
              var username: String,
              var email: String,
              var password: String,
              @OneToOne(cascade = [CascadeType.ALL])
              var pedidosRealizados: Pedidos = Pedidos()) {

    fun nombreDeUsuario(): String{
        return this.username
    }

    fun emailUsuario(): String {
        return this.email
    }

    fun contraseña(): String {
        return this.password
    }

    fun pedidosQueRealizo(): MutableList<Producto> {
        return this.pedidosRealizados.productosEnPedidos()
    }

    fun setEncodePassword(encodePassword: String) {
        this.password = encodePassword
    }
}
