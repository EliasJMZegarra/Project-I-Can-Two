package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Cita;
import upc.com.pe.trabajo_v1.repository.CitaRepository;

import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository repository;

    @Transactional
    public Cita registar(Cita cita){return repository.save(cita);}
    public List<Cita> listado(){
        return repository.findAll();
    }
    public Cita actualizar(Cita cita) throws Exception {
        repository.findById(cita.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return repository.save(cita);
    }
    public Cita eliminar(Integer codigo) throws Exception{
        Cita cita = repository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repository.delete(cita);
        return cita;
    }
}
