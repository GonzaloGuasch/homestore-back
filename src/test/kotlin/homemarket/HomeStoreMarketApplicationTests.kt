package homemarket

import homemarket.model.Producto
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
