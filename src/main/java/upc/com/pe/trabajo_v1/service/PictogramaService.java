package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Pictograma;
import upc.com.pe.trabajo_v1.repository.PictogramaRepository;

import java.util.List;

@Service
public class PictogramaService {
    @Autowired
    private PictogramaRepository pictogramaRepository;
    @Transactional
    public Pictograma registar(Pictograma pictograma){return pictogramaRepository.save(pictograma);}
    public List<Pictograma> listado(){
        return pictogramaRepository.findAll();
    }
    public Pictograma actualizar(Pictograma pictograma) throws Exception {
        pictogramaRepository.findById(pictograma.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return pictogramaRepository.save(pictograma);
    }
    public Pictograma eliminar(Integer codigo) throws Exception{
        Pictograma pictograma = pictogramaRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        pictogramaRepository.delete(pictograma);
        return pictograma;
    }
}
