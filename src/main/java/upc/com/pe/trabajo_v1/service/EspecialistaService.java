package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Especialista;
import upc.com.pe.trabajo_v1.repository.EspecialistaRepository;

import java.util.List;

@Service
public class EspecialistaService {
    @Autowired
    private EspecialistaRepository repository;

    @Transactional
    public Especialista registar(Especialista especialista){return repository.save(especialista);}
    public List<Especialista> listado(){
        return repository.findAll();
    }
    public Especialista actualizar(Especialista especialista) throws Exception {
        repository.findById(especialista.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return repository.save(especialista);
    }
    public Especialista eliminar(Integer codigo) throws Exception{
        Especialista especialista = repository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repository.delete(especialista);
        return especialista;
    }
}
