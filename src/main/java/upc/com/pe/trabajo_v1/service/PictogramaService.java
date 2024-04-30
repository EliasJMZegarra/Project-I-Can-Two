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

    public Pictograma eliminar(Integer codigo){
        Pictograma pictograma = pictogramaRepository.findById(codigo).orElse(null);
        if(pictograma != null){
            pictogramaRepository.delete(pictograma);
        }
        return pictograma;
    }
}
