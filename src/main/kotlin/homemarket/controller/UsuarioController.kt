package homemarket.controller

import homemarket.model.ProductoCantidad
import homemarket.model.Usuario
import homemarket.service.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Usuarios")
@CrossOrigin(origins = ["http://localhost:3000"])
class UsuarioController(private val usuarioService: UsuarioService){

    @PostMapping("/crearUsuario")
    fun crearUsuario(@RequestBody usuario: Usuario) = this.usuarioService.saveUser(usuario)

    @GetMapping("/logIn/{user_email}/{user_password}")
    fun login(@PathVariable user_email: String, @PathVariable user_password: String) = this.usuarioService.loginUser(user_email, user_password)

    @GetMapping("/getUser/{userName}")
    fun getUserByname(@PathVariable userName: String) = this.usuarioService.getByUsername(userName)

    @PostMapping("/GuardarFactura/{username}")
    fun guardarFactura(@RequestBody productoConCantidad: ProductoCantidad,
                       @PathVariable username: String ) = this.usuarioService.guardarFactura(productoConCantidad, username)
}