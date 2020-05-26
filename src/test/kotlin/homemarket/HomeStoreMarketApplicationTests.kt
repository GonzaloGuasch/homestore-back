package homemarket

import homemarket.model.Producto
import homemarket.model.ListaDeCarrito
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductoTest {

	private val producto_uno = Producto("turron fibra", 100, "SKU 12590", "c/u", 100)

	@Test
	fun test001UnProductoTieneUnNombreUnPrecioEnPesosUnIdentificadorYUnaUnidad() {


		assertEquals(producto_uno.nombre(), "TURRON FIBRA")
		assertEquals(producto_uno.precio(), 100)
		assertEquals(producto_uno.id(), "SKU 12590")
		assertEquals(producto_uno.unidad(), "C/U")
		assertTrue(producto_uno.tieneStock())
	}

	@Test
	fun test002unProductoPuedePonerceEnOfertaYSalirDeEsta(){
		producto_uno.crearOferta()
		assertTrue(producto_uno.oferta())

		producto_uno.terminarOferta()
		assertFalse(producto_uno.oferta())
	}
	@Test
	fun test003UnProductoDisminuyeYAUmentaSuStock(){
		producto_uno.disminuirStock(101)
		assertFalse(producto_uno.tieneStock())

		producto_uno.agregarStock(1)
		assertTrue(producto_uno.tieneStock())
	}

}

class ListaDeCarritoTest{
	var lista = ListaDeCarrito()

	@Test
	fun test001UnaListaDeCarritoNoTieneNingunProducto(){
		assertEquals(lista.productosEnLista().size, 0)
	}

	@Test
	fun test002SeAgregaUnProductoALaLista(){
		lista.agregarProducto("SKU 12590", 30)

		assertEquals(lista.productosEnLista().size, 1)
		assertEquals(lista.cantidadPorElProducto("SKU 12590"), 30)
	}
	@Test
	fun test003SiElProductoYaEstaSeSumaLosValores(){
		lista.agregarProducto("SKU 12590", 30)
		lista.agregarProducto("SKU 12590", 30)
		lista.agregarProducto("SKU 12590", 30)
		lista.agregarProducto("SKU 12590", 30)

		assertEquals(lista.productosEnLista().size, 1)
		assertEquals(lista.cantidadPorElProducto("SKU 12590"), 120)
	}
}