package homemarket.Repositories

import homemarket.model.ListaDeCarrito
import homemarket.model.ListaWrapper
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ListaDeCarritoRepository: CrudRepository<ListaDeCarrito, Long> {

}