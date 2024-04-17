package upc.com.pe.trabajo_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.com.pe.trabajo_v1.entities.Rol;
import upc.com.pe.trabajo_v1.entities.Usuario;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}
