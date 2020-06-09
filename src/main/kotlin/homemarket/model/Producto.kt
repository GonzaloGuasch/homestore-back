package homemarket.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Producto(val nombre: String,
               var precio: Int,
               @Id
               val id: String,
               val unidad: String,
               var stock: Int,
               var rubro: String,
               var subrubro: String
               ): Serializable {


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

    fun tieneStockDisponible(cantidadParaPedido: Int): Boolean {
        return this.stock >= cantidadParaPedido
    }

}
