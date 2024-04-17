package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Comentario;
import upc.com.pe.trabajo_v1.entities.Testimonio;
import upc.com.pe.trabajo_v1.repository.TestimonioRepository;

import java.util.List;

@Service
public class TestimonioService {
    @Autowired
    private TestimonioRepository testimonioRepository;
    @Transactional
    public Testimonio registar(Testimonio testimonio){return testimonioRepository.save(testimonio);}

    public List<Testimonio> listado(){
        return testimonioRepository.findAll();
    }

    public Testimonio actualizarTestimonio(Testimonio testimonio) throws Exception {
        testimonioRepository.findById(testimonio.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return testimonioRepository.save(testimonio);
    }

    public Testimonio eliminarTestionio(Integer codigo) throws Exception{
        Testimonio testimonio = testimonioRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        testimonioRepository.delete(testimonio);
        return testimonio;
    }
}
