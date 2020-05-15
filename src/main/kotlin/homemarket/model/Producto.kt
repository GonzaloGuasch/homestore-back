package homemarket.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Producto(private val nombre: String,
               private val precio: Int,
               @Id
               private val id: String,
               private val unidad: String,
               private var estaDeOferta: Boolean = false,
               private var descuento: Int = 0) {


    fun getNombre(): String {
        return this.nombre.toUpperCase()
    }

    fun getPrecio(): Int {
        return this.precio
    }

    fun getId(): String {
        return this.id.toUpperCase()
    }

    fun getUnidad(): String {
        return this.unidad.toUpperCase()
    }

    fun estaDeOferta(): Boolean {
        return this.estaDeOferta
    }

    fun crearOferta() {
        this.estaDeOferta = true
    }

    fun terminarOferta() {
        this.estaDeOferta = false
    }

}
