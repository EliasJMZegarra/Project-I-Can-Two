package upc.com.pe.trabajo_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.com.pe.trabajo_v1.entities.Comentario;
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
