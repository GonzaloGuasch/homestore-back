package homemarket.Repositories

import homemarket.model.Producto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductoRepository: CrudRepository<Producto, String> {

    @Query("SELECT * FROM producto WHERE nombre like '%:nombreProducto%'", nativeQuery = true)
     fun buscarProductoQueContenga(@Param("nombre") nombre: String): MutableList<Producto>

}