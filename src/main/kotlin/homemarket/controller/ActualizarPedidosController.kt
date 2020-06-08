package homemarket.controller

import homemarket.model.ListaProductosWrapper
import homemarket.service.ProductoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ActualizarProducto")
//@CrossOrigin(origins = ["http://localhost:3000"])
class ActualizarPedidosController(var productoService: ProductoService){

    @PostMapping("/Actualizar")
    fun actualizarProductos(@RequestBody listaProductos: ListaProductosWrapper) = this.productoService.actualizarProductos(listaProductos)
}