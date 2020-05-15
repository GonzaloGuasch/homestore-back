package homemarket.Repositories

import homemarket.model.Producto
import org.springframework.data.repository.CrudRepository


interface ProductoRepository: CrudRepository<Producto, String> {

}