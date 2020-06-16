package homemarket.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Pedido(var nombreProducto: String,
             var cantidad: Int,
             var precio: Int,
             var id_producto: Int,
             @Id @GeneratedValue var id: Long = 0)
{
}