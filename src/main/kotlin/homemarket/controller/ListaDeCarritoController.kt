package homemarket.controller

import homemarket.Repositories.ListaDeCarritoRepository
import homemarket.model.ListaDeCarrito
import homemarket.model.ListaWrapper
import homemarket.service.ListaDeCarritoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ListaProducto")
@CrossOrigin(origins = ["http://localhost:3000"])
class ListaDeCarritoController(private var listaDeCarritoService: ListaDeCarritoService){

    @GetMapping("/")
    fun index() = "Its working"

    @GetMapping("/TodaLaLista")
    fun allList() = this.listaDeCarritoService.traerTodaLaLista()

    @GetMapping("/EsCarritoVacio/{id}")
    fun isEmpty() = this.listaDeCarritoService.estaVacio()

    @GetMapping("/TodosLosProductosDeCarrito/{id}")
    fun productosDe(@PathVariable id: Long) = this.listaDeCarritoService.traerProductosDe(id)

    @GetMapping("/GetListaById/{id}")
    fun getById(@PathVariable id: Long) = this.listaDeCarritoService.traerPorId(id)

    @PostMapping("/crearLista")
    fun crearList() = this.listaDeCarritoService.crearLista()

    @PostMapping("/AgregarALista")
    fun agregarALista(@RequestBody listaWrapper: ListaWrapper) = this.listaDeCarritoService.agregarProductors(listaWrapper)

    @PostMapping("/BorrarTodosLosProductos/{id}")
    fun borrarLista(@PathVariable id: Long) = this.listaDeCarritoService.borrarProductosDe(id)
}