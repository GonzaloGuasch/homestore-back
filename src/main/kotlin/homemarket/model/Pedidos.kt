package homemarket.model

import homemarket.model.exception.PedidoNoValidoException
import javax.persistence.*

@Entity
class Pedidos(@ElementCollection
             private var parProductoCantidad: MutableSet<Pair<Producto, Int>> =  mutableSetOf(),
              @Id @GeneratedValue
             private var id:Long = 0) {


    fun productosEnPedidos(): MutableList<Producto> {
        var listaDePedidos = mutableListOf<Producto>()
        this.parProductoCantidad.forEach { parClaveCantidad -> listaDePedidos.add(parClaveCantidad.first) }
     return listaDePedidos
    }

    fun agregarProducto(pair: Pair<Producto, Int>) {
        if (pair.first.tieneStockDisponible(pair.second)) {
            this.parProductoCantidad.add(pair)
        } else {
            throw PedidoNoValidoException("No hay suficiente stock para este pedido!")
        }
    }

}
