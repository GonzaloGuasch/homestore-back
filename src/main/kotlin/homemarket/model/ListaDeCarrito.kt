package homemarket.model

import javax.persistence.*

@Entity
class ListaDeCarrito(@ElementCollection
                     var productosEnLista: MutableMap<String, Int> = mutableMapOf(),
                     @Id
                     var id: Long = 0){

    fun productosEnLista(): MutableSet<String>{
        return this.productosEnLista.keys
    }

    fun agregarProducto(id_producto: String, cantidad: Int) {
        if(this.productosEnLista.containsKey(id_producto)){
            var cantidadEnCarro = this.productosEnLista.get(id_producto)
            var nuevacantidadEnCarro = cantidadEnCarro?.plus(cantidad)
            this.productosEnLista.put(id_producto, nuevacantidadEnCarro!!)
        }else {
            this.productosEnLista.putIfAbsent(id_producto, cantidad)
        }

    }

    fun cantidadPorElProducto(id_producto: String): Int {
        return this.productosEnLista.get(id_producto)!!
    }

    fun estaVacio(): Boolean {
        return this.productosEnLista.isEmpty()
    }

    fun vaciarProductos() {
        this.productosEnLista = mutableMapOf()
    }


}
