package homemarket.service

import homemarket.Repositories.ListaDeCarritoRepository
import homemarket.Repositories.ProductoRepository
import homemarket.model.ListaDeCarrito
import homemarket.model.ListaWrapper
import org.springframework.stereotype.Service

@Service
class ListaDeCarritoService(private val listaDeCarritoRepository: ListaDeCarritoRepository,
                            private val productoRepository: ProductoRepository) {



    fun crearLista() {
        this.listaDeCarritoRepository.save(ListaDeCarrito())
    }

    fun agregarProductors(listaWrapper: ListaWrapper): ListaDeCarrito {
        val carrito = findById(0)
        carrito.agregarProducto(listaWrapper.idProducto, listaWrapper.cantidad)

        this.listaDeCarritoRepository.save(carrito)

        return carrito
    }

    fun traerTodaLaLista() = this.listaDeCarritoRepository.findAll()

    fun traerPorId(id: Long): ListaDeCarrito {
        return this.listaDeCarritoRepository.findById(id).get()
    }

    fun estaVacio(): Boolean {
        return findById(0).estaVacio()
    }

    private fun findById(id:Long) = this.listaDeCarritoRepository.findById(id).get()

    fun traerProductosDe(id: Long): MutableMap<String, Int> {
        return productosDe(0)
    }

    private fun productosDe(id: Long) = this.listaDeCarritoRepository.findById(id).get().productosEnLista

    fun borrarProductosDe(id: Long){
        val carrito = this.findById(0)
        carrito.vaciarProductos()
        this.listaDeCarritoRepository.save(carrito)
    }
}