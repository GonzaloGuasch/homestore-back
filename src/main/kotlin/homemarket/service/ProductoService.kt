package homemarket.service

import homemarket.Repositories.ProductoRepository
import homemarket.model.Producto
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.util.*


@Service
class ProductoService(private var productoRepository: ProductoRepository){


    fun findAll(numero_pagina: String): MutableIterable<Producto> {
        return this.productoRepository.traerProductosDePagina(numero_pagina.toInt())
    }

    fun findById(id: String): Optional<Producto> {
        return this.productoRepository.findById(id)
    }

    fun buscarProductosCon(keyword: String): Any {
        val keywordUppercase = keyword.toUpperCase()
        return this.productoRepository.buscarProductoQueContenga(keywordUppercase)
    }

    fun guardarSinImagen(producto: Producto): Producto {
        return this.productoRepository.save(producto)
    }

    fun traerProductosDeRubro(unRubro: String): Any {
        return this.productoRepository.traerTodosDelRubro(unRubro)
    }

    fun decrementarStock(nombreProducto: String, cantidad: Int) {
        val producto: Producto = this.productoRepository.findByName(nombreProducto)
        producto.disminuirStock(cantidad)

        this.guardarSinImagen(producto)

    }


}