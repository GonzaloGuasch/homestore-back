package homemarket.controller

import homemarket.Exceptions.MailEnUsoException
import homemarket.Exceptions.UsuarioNoExistenteException
import homemarket.model.FacturaWrapper
import homemarket.model.Usuario
import homemarket.model.UsuarioFacturaInfoWrapper
import homemarket.service.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Usuarios")
//@CrossOrigin(origins = ["http://localhost:3000"])
class UsuarioController(private val usuarioService: UsuarioService){

    @PostMapping("/crearUsuario")
    fun crearUsuario(@RequestBody usuario: Usuario) = this.usuarioService.saveUser(usuario)

    @GetMapping("/logIn/{user_email}/{user_password}")
    fun login(@PathVariable user_email: String,
              @PathVariable user_password: String): Any {
        val usuarioOrNull = this.usuarioService.loginUser(user_email, user_password)
            if(usuarioOrNull == null){
                throw UsuarioNoExistenteException("El usuario no existe")
            }else{
                return usuarioOrNull
            }
    }
    @GetMapping("/getUser/{userName}")
    fun getUserByname(@PathVariable userName: String): Any {
        val usuarioOrNull = this.usuarioService.getByUsername(userName)
            if(usuarioOrNull == null){
                throw UsuarioNoExistenteException("El usuario no existe")
            }else{
                return usuarioOrNull
            }
    }

    @PostMapping("/GuardarFactura")
    fun guardarFactura(@RequestBody facturaWrapper: FacturaWrapper): Any {
        val usuarioOrNull = this.usuarioService.guardarFactura(facturaWrapper)
        if(usuarioOrNull == null){
            throw UsuarioNoExistenteException("No se encontro el usuario con ese nombre")
        }else{
            return usuarioOrNull
        }
    }

    @GetMapping("/PedidosDe/{email}")
    fun pedidosDe(@PathVariable email: String) = this.usuarioService.pedidosDe(email)

    @PostMapping("/GuardarInfoDeFactura")
    fun guardarInfoDeFactura(@RequestBody usuarioFacturaInfoWrapper: UsuarioFacturaInfoWrapper) = this.usuarioService.guardarInfoFacturaDeUsuario(usuarioFacturaInfoWrapper)

    @GetMapping("/mailExistente/{user_email_register}")
    fun checkEmail(@PathVariable user_email_register: String): Any {
        if(this.usuarioService.checkEmail(user_email_register) == 1){
            throw MailEnUsoException("Email en uso");
        }else{
            return 200
        }
    }
}