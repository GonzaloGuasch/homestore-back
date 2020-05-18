package homemarket.model

class ListaDeCarrito(private var productosEnLista: MutableMap<Producto, Int> = mutableMapOf()){

    fun productosEnLista(): MutableSet<Producto>{
        return this.productosEnLista.keys
    }

    fun agregarProducto(producto: Producto, cantidad: Int) {
        if(this.productosEnLista.containsKey(producto)){
            var cantidadEnCarro = this.productosEnLista.get(producto)
            var nuevacantidadEnCarro = cantidadEnCarro?.plus(cantidad)
            this.productosEnLista.put(producto, nuevacantidadEnCarro!!)
        }else {
            this.productosEnLista.putIfAbsent(producto, cantidad)
        }

    }

    fun cantidadPorElProducto(id_producto: Producto): Int {
        return this.productosEnLista.get(id_producto)!!
    }


}
