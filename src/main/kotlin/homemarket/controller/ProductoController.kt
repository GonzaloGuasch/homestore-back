package homemarket.controller

import homemarket.model.Producto
import homemarket.service.ProductoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Producto")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductoController(private var productoService: ProductoService) {

    @GetMapping("/traerTodos/{numero_pagina}")
    fun todosLosProductos(@PathVariable numero_pagina: String) = this.productoService.findAll(numero_pagina)

    @GetMapping("/TraerDeRubro/{unRubro}")
    fun traerLosDeRubro(@PathVariable unRubro: String) = this.productoService.traerProductosDeRubro(unRubro)

    @GetMapping("/getProducto/{id}")
    fun productoPorId(@PathVariable id: String) = this.productoService.findById(id)

    @PostMapping("/guardarProducto/{path_imagen}")
    fun guardarProducto(@RequestBody producto: Producto, @PathVariable path_imagen: String) = this.productoService.save(producto, path_imagen)

    @PostMapping("/guardarProducto")
    fun guardarProductoSinImagen(@RequestBody producto: Producto) = this.productoService.guardarSinImagen(producto)

    @GetMapping("/productosQueContengan/{keyword}")
    fun productosCon(@PathVariable keyword: String) = this.productoService.buscarProductosCon(keyword)
}