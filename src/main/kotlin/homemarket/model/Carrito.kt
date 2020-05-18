package homemarket.model

class Carrito(var valorTotal: Int = 0,
              private var productosEnCarrito: ListaDeCarrito = ListaDeCarrito()){

    fun valorTotal(): Int{
        return 0
    }

    fun productosEnCarrito(): MutableSet<Producto>{
        return this.productosEnCarrito.productosEnLista()
    }
}
