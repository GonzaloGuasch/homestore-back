package homemarket.controller

import homemarket.model.Producto
import homemarket.service.ProductoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Producto")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductoController(private var productoService: ProductoService) {

    @GetMapping("/traerTodos")
    fun todosLosProductos() = this.productoService.findAll()

    @GetMapping("/getProducto/{id}")
    fun productoPorId(@PathVariable id: String) = this.productoService.findById(id)

    @PostMapping("/guardarProducto")
    fun guardarProducto(@RequestBody producto: Producto) = this.productoService.save(producto)

    @GetMapping("/productosQueContengan/{keyword}")
    fun productosCon(@PathVariable keyword: String) = this.productoService.buscarProductosCon(keyword)
}