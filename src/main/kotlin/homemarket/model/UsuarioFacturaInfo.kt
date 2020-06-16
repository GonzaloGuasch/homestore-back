package homemarket.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class UsuarioFacturaInfo(   var nombre: String,
                            var apellido: String,
                            var pais: String,
                            var provincia: String,
                            var localidad: String,
                            var direccion: String,
                            var observaciones: String?,
                            var telefono: String,
                            var comentarios: String?,
                            var codigoPostal: String,
                            @Id @GeneratedValue
                            var id: Long = 0){
}