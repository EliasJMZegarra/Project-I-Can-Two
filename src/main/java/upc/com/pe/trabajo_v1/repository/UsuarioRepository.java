package upc.com.pe.trabajo_v1.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.com.pe.trabajo_v1.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByUsername(String username);

    //BUSCAR POR NOMBRE
    @Query("select count(u.username) from Usuario u where u.username =:username")
    public int buscarUsuarioporNombre(@Param("username") String nombre);

    @Transactional
    @Modifying
        @Query(value = "INSERT INTO users_roles(usuario_id, rol_id) VALUES (usurio_id, rol_id)", nativeQuery = true)
    public Integer insertUserRol (@Param("usuario_id") Integer usuario_id, @Param("rol_id") Integer rol_id);
}
