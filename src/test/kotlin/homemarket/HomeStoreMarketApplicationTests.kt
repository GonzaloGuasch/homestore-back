package homemarket

import homemarket.model.Pedidos
import homemarket.model.Producto
import homemarket.model.Usuario
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

class PedidosTest{

	private val producto = Producto("turron fibra", 100, "SKU 12590", "c/u", 100, "GOLOSINAS", "Turrones")

	@Test
	fun aUnPedidoSeLeAgreganTuplasDeProductoCantidad(){
		val pedido = Pedidos()
		pedido.agregarProducto(Pair(producto, 20))

		assertEquals(pedido.productosEnPedidos(), arrayListOf(producto))
	}

	@Test()
	fun siUnaCantidadEsMayorAlPedidoArrojaUnError(){
		val pedido = Pedidos()
		assertFailsWith<PedidoNoValidoException> {
			pedido.agregarProducto(Pair(producto, 200))
		}
	}
}

class UsuariosTest{

	@Test()
	fun unUsuarioTieneUnUsernameEmailContraseñaYUnaListaDePedidos(){
		val usuario = Usuario("Gonzalo", "Gonzalo@gmail.com", "1234")

		assertEquals(usuario.nombreDeUsuario(), "Gonzalo")
		assertEquals(usuario.emailUsuario(), "Gonzalo@gmail.com")
		assertEquals(usuario.contraseña(), "1234")
		assertEquals(usuario.pedidosQueRealizo().size, 0)
	}
}
