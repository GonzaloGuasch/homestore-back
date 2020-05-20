package homemarket.service

import homemarket.Repositories.ProductoRepository
import homemarket.model.Producto
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductoService(private var productoRepository: ProductoRepository){


    fun findAll(): MutableIterable<Producto> {
        return this.productoRepository.findAll()
    }

    fun findById(id: String): Optional<Producto> {
        return this.productoRepository.findById(id)
    }

    fun save(producto: Producto) {
        this.productoRepository.save(producto)
    }

    fun buscarProductosCon(keyword: String): Any {
        val keywordUppercase = keyword.toUpperCase()
        return this.productoRepository.buscarProductoQueContenga(keywordUppercase)
    }


}