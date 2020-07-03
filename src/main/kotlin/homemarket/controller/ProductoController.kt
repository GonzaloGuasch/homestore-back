package homemarket.controller

import homemarket.Exceptions.ProductoNoEncotradoException
import homemarket.Exceptions.StockInsuficienteParaProductoException
import homemarket.model.ListaProducosDecrementar
import homemarket.model.Producto
import homemarket.service.ProductoService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/Producto")
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductoController(private var productoService: ProductoService) {

    @GetMapping("/traerTodos/{numero_pagina}")
    fun todosLosProductos(@PathVariable numero_pagina: String) = this.productoService.findAllByPage(numero_pagina)

    @GetMapping("/TraerDeRubro/{unRubro}/{limit}")
    fun traerLosDeRubro(@PathVariable unRubro: String,
                        @PathVariable limit: String) = this.productoService.traerProductosDeRubro(unRubro, limit)

    @GetMapping("/getProducto/{id}")
    fun productoPorId(@PathVariable id: String): Any{
        val productoOrNull = this.productoService.findById(id)
        if(productoOrNull == null){
            throw ProductoNoEncotradoException("No se encontro ningun producto")
        }else{
            return productoOrNull
        }
    }

    @PostMapping("/guardarProducto")
    fun guardarProductoSinImagen(@RequestBody producto: Producto) = this.productoService.guardarSinImagen(producto)

    @PostMapping("/imagenProducto/{id_Producto}")
    fun imagen( @RequestParam("imagenProducto") imagenProducto: MultipartFile,
                @PathVariable id_Producto: String) = this.productoService.guardarImagenDeProducto(imagenProducto, id_Producto)

    @GetMapping("/productosQueContengan/{keyword}")
    fun productosCon(@PathVariable keyword: String){
        val productoOrEmptyList = this.productoService.buscarProductosCon(keyword)
        if(productoOrEmptyList.size == 0){
            throw ProductoNoEncotradoException("No se encontro ningun producto")
        }else{
            productoOrEmptyList
        }
    }

    @PostMapping("/decrementarStock")
    fun decrementarStockDeProductos(@RequestBody productos: ListaProducosDecrementar) = this.productoService.decrementarStockParaProductos(productos)

    @PostMapping("/aumentarStock")
    fun aumentarStcokDeProductos(@RequestBody productos: ListaProducosDecrementar) = this.productoService.aumentarStockParaProductos(productos)

    @GetMapping("/hayStockPara/{id}/{stock}")
    fun hayStockPara(@PathVariable id: String,
                     @PathVariable stock: String): Any {
        val stockProducto = this.productoService.stockDe(id)
        if(stockProducto >= stock.toInt()){
            return 200
        }else{
            throw StockInsuficienteParaProductoException("El producto tiene disponible $stockProducto de stock")
        }
    }
}