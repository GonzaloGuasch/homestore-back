package homemarket

import homemarket.model.Producto
import homemarket.model.Usuario
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
	fun test003UnProductoDisminuyeYAUmentaSuStock(){
		producto_uno.disminuirStock(101)
		assertFalse(producto_uno.tieneStock())

		producto_uno.agregarStock(1)
		assertTrue(producto_uno.tieneStock())
	}

}

class UsuariosTest{

	val usuario = Usuario("Gonzalo", "Gonzalo@gmail.com", "1234")
	val producto = Producto("turron fibra", 100, "SKU 12590", "c/u", 100, "GOLOSINAS", "Turrones")

	@Test()
	fun unUsuarioTieneUnUsernameEmailContraseñaYUnaListaDePedidos(){

		assertEquals(usuario.nombreDeUsuario(), "Gonzalo")
		assertEquals(usuario.emailUsuario(), "Gonzalo@gmail.com")
		assertEquals(usuario.contraseña(), "1234")
		assertEquals(usuario.pedidosQueRealizo().size, 0)
	}

	@Test
	fun unUsuarioAgregaUnPedido(){
		usuario.realizarPedido(producto.nombre,  1,23,producto.precio)

		assertEquals(usuario.pedidosQueRealizo().size, 1)
	}
}
