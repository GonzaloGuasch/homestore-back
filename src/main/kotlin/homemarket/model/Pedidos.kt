package homemarket.model

import homemarket.model.exception.PedidoNoValidoException
import javax.persistence.*


class Pedidos(
              var parProductoCantidad: MutableMap<Producto, Int> =  mutableMapOf(),
              var id:Long = 0) {


    fun productosEnPedidos(): MutableList<Producto> {
        var listaDePedidos = mutableListOf<Producto>()
        this.parProductoCantidad.forEach { parClaveCantidad -> listaDePedidos.add(parClaveCantidad.key) }
     return listaDePedidos
    }

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.tieneStockDisponible(cantidad)) {
            val pair = Pair(producto, cantidad)
            this.parProductoCantidad.put(producto, cantidad)
        } else {
            throw PedidoNoValidoException("No hay suficiente stock para este pedido!")
        }
    }

}
