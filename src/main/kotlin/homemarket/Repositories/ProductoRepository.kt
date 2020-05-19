package homemarket.Repositories

import homemarket.model.Producto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductoRepository: CrudRepository<Producto, String> {

}