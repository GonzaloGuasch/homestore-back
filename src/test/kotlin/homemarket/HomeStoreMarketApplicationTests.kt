package homemarket

import homemarket.model.Pedido
import homemarket.model.Producto
import homemarket.model.exception.PedidoNoValidoException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ProductoTest {

	private val producto_uno = Producto("turron fibra", 100, "SKU 12590", "c/u", 100, "GOLOSINAS", "Turrones")

	@Test
	fun test001UnProductoTieneUnNombreUnPrecioEnPesosUnIdentificadorYUnaUnidad() {

		assertEquals(producto_uno.nombre(), "TURRON FIBRA")
		assertEquals(producto_uno.precio(), 100)
		assertEquals(producto_uno.id(), "SKU 12590")
		assertEquals(producto_uno.unidad(), "C/U")
		assertTrue(producto_uno.tieneStock())
		assertEquals(producto_uno.rubroProducto(), "GOLOSINAS")
		assertEquals(producto_uno.subSubro(), "Turrones")
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

class PedidoTest{

	private val producto = Producto("turron fibra", 100, "SKU 12590", "c/u", 100, "GOLOSINAS", "Turrones")

	@Test
	fun aUnPedidoSeLeAgreganTuplasDeProductoCantidad(){
		val pedido = Pedido()
		pedido.agregarProducto(Pair(producto, 20))

		assertEquals(pedido.productosEnPedido(), arrayListOf(producto))
	}

	@Test()
	fun siUnaCantidadEsMayorAlPedidoArrojaUnError(){
		val pedido = Pedido()
		assertFailsWith<PedidoNoValidoException> {
			pedido.agregarProducto(Pair(producto, 200))
		}
	}
}

