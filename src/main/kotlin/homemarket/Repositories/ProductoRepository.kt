package homemarket.Repositories

import homemarket.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductoRepository: JpaRepository<Producto, String> {

    @Query("SELECT * FROM producto WHERE nombre like %:nombreProducto%", nativeQuery = true)
     fun buscarProductoQueContenga(@Param("nombreProducto") nombreProducto: String): MutableList<Producto>


    @Query("SELECT * FROM producto WHERE rubro = :unRubro", nativeQuery = true)
    fun traerTodosDelRubro(@Param("unRubro") unRubro: String): MutableList<Producto>

    @Query("SELECT * FROM producto LIMIT 8 OFFSET :numeroPagina", nativeQuery = true)
    fun traerProductosDePagina(@Param("numeroPagina") numeroPagina: Int): MutableIterable<Producto>

    @Query("SELECT * FROM producto WHERE nombre = :nombreproducto", nativeQuery = true)
    fun findByName(@Param("nombreproducto") nombreproducto: String): Producto

}