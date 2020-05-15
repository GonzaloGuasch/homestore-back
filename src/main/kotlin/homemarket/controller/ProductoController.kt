package homemarket.controller

import homemarket.Repositories.ProductoRepository
import homemarket.model.Producto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Producto")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductoController(private var productoRepository: ProductoRepository) {

    @GetMapping("/traerTodos")
    fun todosLosProductos() = this.productoRepository.findAll()

    @GetMapping("/hola")
    fun index() = "Hola"

    @GetMapping("/getProducto/{id}")
    fun productoPorId(@PathVariable id: String) = this.productoRepository.findById(id)

    @PostMapping("/guardarProducto")
    fun guardarProducto(@RequestBody producto: Producto) = this.productoRepository.save(producto)
}