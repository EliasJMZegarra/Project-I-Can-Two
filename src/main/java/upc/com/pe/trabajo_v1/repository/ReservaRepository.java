package upc.com.pe.trabajo_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.com.pe.trabajo_v1.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
