package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Clinica;
import upc.com.pe.trabajo_v1.repository.ClinicaRepository;

import java.util.List;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository repository;

    @Transactional
    public Clinica registar(Clinica clinica){return repository.save(clinica);}
    public List<Clinica> listado(){
        return repository.findAll();
    }
    public Clinica actualizar(Clinica clinica) throws Exception {
        repository.findById(clinica.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return repository.save(clinica);
    }
    public Clinica eliminar(Integer codigo) throws Exception{
        Clinica clinica = repository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repository.delete(clinica);
        return clinica;
    }
}
