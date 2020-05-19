package homemarket.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Producto(val nombre: String,
               val precio: Int,
               @Id
               val id: String,
               val unidad: String,
               var estaDeOferta: Boolean = false,
               var descuento: Int = 0) {


    fun nombre(): String {
        return this.nombre.toUpperCase()
    }

    fun precio(): Int {
        return this.precio
    }

    fun id(): String {
        return this.id.toUpperCase()
    }

    fun unidad(): String {
        return this.unidad.toUpperCase()
    }

    fun oferta(): Boolean {
        return this.estaDeOferta
    }

    fun crearOferta() {
        this.estaDeOferta = true
    }

    fun terminarOferta() {
        this.estaDeOferta = false
    }

}
