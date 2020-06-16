package homemarket.model

class UserResponse(val username: String,
                   val email: String,
                   val pedidosRealizados: MutableSet<Pedido>?,
                   val informacionEnFactura: UsuarioFacturaInfo?) {

}
