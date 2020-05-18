package homemarket

import homemarket.model.Producto
import homemarket.model.Carrito
import homemarket.model.ListaDeCarrito
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductoTest {

	private val producto_uno = Producto("turron fibra", 100, "SKU 12590", "c/u")

	@Test
	fun test001UnProductoTieneUnNombreUnPrecioEnPesosUnIdentificadorYUnaUnidad() {


		assertEquals(producto_uno.getNombre(), "TURRON FIBRA")
		assertEquals(producto_uno.getPrecio(), 100)
		assertEquals(producto_uno.getId(), "SKU 12590")
		assertEquals(producto_uno.getUnidad(), "C/U")
	}

	@Test
	fun test002unProductoPuedePonerceEnOfertaYSalirDeEsta(){
		producto_uno.crearOferta()
		assertTrue(producto_uno.estaDeOferta())

		producto_uno.terminarOferta()
		assertFalse(producto_uno.estaDeOferta())
	}

}

class CarritoTest{
	@Test
	fun test001UnCarritoEmpiezaVacio(){
		var carrito = Carrito()

		assertEquals(carrito.valorTotal(), 0)
		assertEquals(carrito.productosEnCarrito().size, 0)
	}
}

class ListaDeCarritoTest{
	var lista = ListaDeCarrito()
	val producto_uno = Producto("turron fibra", 100, "SKU 12590", "c/u")


	@Test
	fun test001UnaListaDeCarritoNoTieneNingunProducto(){
		assertEquals(lista.productosEnLista().size, 0)
	}

	@Test
	fun test002SeAgregaUnProductoALaLista(){
		lista.agregarProducto(producto_uno, 30)

		assertEquals(lista.productosEnLista().size, 1)
		assertEquals(lista.cantidadPorElProducto(producto_uno), 30)
	}
	@Test
	fun test003SiElProductoYaEstaSeSumaLosValores(){
		lista.agregarProducto(producto_uno, 30)
		lista.agregarProducto(producto_uno, 30)
		lista.agregarProducto(producto_uno, 30)
		lista.agregarProducto(producto_uno, 30)

		assertEquals(lista.productosEnLista().size, 1)
		assertEquals(lista.cantidadPorElProducto(producto_uno), 120)
	}
}