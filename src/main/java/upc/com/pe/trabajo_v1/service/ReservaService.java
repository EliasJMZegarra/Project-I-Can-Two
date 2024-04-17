package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Reserva;
import upc.com.pe.trabajo_v1.repository.ReservaRepository;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repository;

    @Transactional
    public Reserva registar(Reserva reserva){return repository.save(reserva);}
    public List<Reserva> listado(){
        return repository.findAll();
    }
    public Reserva actualizar(Reserva reserva) throws Exception {
        repository.findById(reserva.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return repository.save(reserva);
    }
    public Reserva eliminar(Integer codigo) throws Exception{
        Reserva reserva = repository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repository.delete(reserva);
        return reserva;
    }
}
