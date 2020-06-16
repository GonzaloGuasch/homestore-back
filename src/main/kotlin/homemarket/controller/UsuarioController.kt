package homemarket.controller

import homemarket.model.FacturaWrapper
import homemarket.model.ProductoCantidad
import homemarket.model.Usuario
import homemarket.model.UsuarioFacturaInfoWrapper
import homemarket.service.UsuarioService
import org.springframework.http.MediaType
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

    @PostMapping("/GuardarFactura")
    fun guardarFactura(@RequestBody facturaWrapper: FacturaWrapper) = this.usuarioService.guardarFactura(facturaWrapper)

    @GetMapping("/PedidosDe/{email}")
    fun pedidosDe(@PathVariable email: String) = this.usuarioService.pedidosDe(email)

    @PostMapping("/GuardarInfoDeFactura")
    fun guardarInfoDeFactura(@RequestBody usuarioFacturaInfoWrapper: UsuarioFacturaInfoWrapper) = this.usuarioService.guardarInfoFacturaDeUsuario(usuarioFacturaInfoWrapper)

    @GetMapping("/mailExistente/{user_email_register}")
    fun checkEmail(@PathVariable user_email_register: String): Any {
        if(this.usuarioService.checkEmail(user_email_register) == 1){
            throw Exception("Email en uso");
        }else{
            return 200
        }
    }
}