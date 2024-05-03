package upc.com.pe.trabajo_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.com.pe.trabajo_v1.entities.Mensaje;
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}
