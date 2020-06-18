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

    fun findById(id: String): Producto {
        return this.productoRepository.findById(id).get()
    }

    fun buscarProductosCon(keyword: String): Any {
        val keywordUppercase = keyword.toUpperCase()
        return this.productoRepository.buscarProductoQueContenga(keywordUppercase)
    }

    fun guardarSinImagen(producto: Producto): Producto {
        return this.productoRepository.save(producto)
    }

    fun traerProductosDeRubro(unRubro: String, limit: String): Any {
        return this.productoRepository.traerTodosDelRubro(unRubro, limit.toInt())
    }

    fun decrementarStock(nombreProducto: String, cantidad: Int) {
        val producto: Producto = this.productoRepository.findByName(nombreProducto)
        producto.disminuirStock(cantidad)

        this.guardarSinImagen(producto)

    }

    fun decrementarStockParaProductos(listaProductos: ListaProducosDecrementar){
        listaProductos.productos.forEach { unProducto -> this.decrementarStock(unProducto.producto, unProducto.cantidad) }
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


}