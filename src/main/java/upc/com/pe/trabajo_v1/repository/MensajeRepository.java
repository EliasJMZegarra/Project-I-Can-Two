package upc.com.pe.trabajo_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.com.pe.trabajo_v1.entities.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}