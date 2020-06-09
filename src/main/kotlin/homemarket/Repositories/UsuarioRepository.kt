package homemarket.Repositories

import homemarket.model.Usuario
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UsuarioRepository: CrudRepository<Usuario, String> {

    @Query("SELECT * FROM usuario WHERE email = :userEmail", nativeQuery = true)
    fun findByEmail(@Param("userEmail") userEmail: String): Usuario?

    @Query("SELECT EXISTS(SELECT * FROM usuario WHERE email = :userEmail)", nativeQuery = true)
    fun checkEmail(@Param("userEmail") userEmail: String): Int
}