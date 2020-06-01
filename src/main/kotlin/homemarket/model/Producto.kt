package homemarket.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Producto(val nombre: String,
               val precio: Int,
               @Id
               val id: String,
               val unidad: String,
               var stock: Int,
               var rubro: String,
               var subrubro: String,
               var estaDeOferta: Boolean = false,
               @Lob
               var imagenProducto: ByteArray? = null,
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

    fun tieneStock(): Boolean {
        return this.stock > 0
    }

    fun disminuirStock(stockParaDisminuir: Int) {
        if(this.stock - stockParaDisminuir < 0){
            this.stock = 0
        }else{
            this.stock = this.stock - stockParaDisminuir
        }
    }

    fun agregarStock(stockParaAgregar: Int) {
        this.stock = this.stock + stockParaAgregar
    }

    fun subSubro(): String {
        return this.subrubro
    }

    fun rubroProducto(): String {
        return this.rubro
    }

    fun settearImagenProducto(imagenProductoEnBytes: ByteArray) {
        this.imagenProducto = imagenProductoEnBytes
    }

    fun tieneStockDisponible(cantidadParaPedido: Int): Boolean {
        return this.stock >= cantidadParaPedido
    }

}
