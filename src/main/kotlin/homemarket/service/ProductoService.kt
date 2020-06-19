package homemarket.service

import homemarket.Repositories.ProductoRepository
import homemarket.model.ListaProducosDecrementar
import homemarket.model.ListaProductosWrapper
import homemarket.model.Producto
import homemarket.model.ProductoActualizarWrapper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class ProductoService(private var productoRepository: ProductoRepository){


    fun findAllByPage(numero_pagina: String): MutableIterable<Producto> {
        return this.productoRepository.traerProductosDePagina(numero_pagina.toInt())
    }

    fun findById(id: String): Producto? {
        val producto = this.productoRepository.findById(id)
        return if(producto.isEmpty){
            null
        }else{
            producto.get()
        }
    }

    fun buscarProductosCon(keyword: String): MutableList<Producto> {
        val keywordUppercase = keyword.toUpperCase()
        return this.productoRepository.buscarProductoQueContenga(keywordUppercase)
    }

    fun guardarSinImagen(producto: Producto): Producto {
        return this.productoRepository.save(producto)
    }

    fun traerProductosDeRubro(unRubro: String, limit: String): Any {
        return this.productoRepository.traerTodosDelRubro(unRubro, limit.toInt())
    }

    fun actualizarProductos(listaProductos: ListaProductosWrapper) {
        return listaProductos.productos.forEach { unProducto -> this.actualizarProducto(unProducto) }
    }

    private fun actualizarProducto(unProducto: ProductoActualizarWrapper): Producto {
        val producto = this.productoRepository.findById(unProducto.CODIGO).get()
        producto.agregarStock(unProducto.STOCK)
        producto.precio = unProducto.PRECIO
        this.productoRepository.save(producto)
        return producto
    }

    fun logInSuperUser(userName: String, userPassword: String): Any {
        if(userName.toUpperCase() == "GONZALO"){
            if(userPassword == "1"){
                return 200
            }
        }else{
            return 404
        }
        return 500
    }

    fun guardarImagenDeProducto(imagen: MultipartFile, id: String): Producto {
        val producto =  this.productoRepository.findById(id).get()
        producto.imagenProducto = imagen.bytes
        this.productoRepository.save(producto)
        return producto
    }

    fun aumentarStockParaProductos(listaProductos: ListaProducosDecrementar) {
        listaProductos.productos.forEach { unProducto -> this.aumentarStock(unProducto.producto, unProducto.cantidad) }
    }
    fun aumentarStock(nombreProducto: String, cantidad: Int) {
        val producto: Producto = this.productoRepository.findByName(nombreProducto)
        producto.agregarStock(cantidad)

        this.guardarSinImagen(producto)

    }
    fun decrementarStockParaProductos(listaProductos: ListaProducosDecrementar){
        listaProductos.productos.forEach { unProducto -> this.decrementarStock(unProducto.producto, unProducto.cantidad) }
    }
    fun decrementarStock(nombreProducto: String, cantidad: Int) {
        val producto: Producto = this.productoRepository.findByName(nombreProducto)
        producto.disminuirStock(cantidad)

        this.guardarSinImagen(producto)

    }

    fun stockDe(idProducto: String): Int {
        val producto = this.productoRepository.findById(idProducto).get()
        return producto.stock
    }

}