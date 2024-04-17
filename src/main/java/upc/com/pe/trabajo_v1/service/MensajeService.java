package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Mensaje;
import upc.com.pe.trabajo_v1.repository.MensajeRepository;

import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    @Transactional
    public Mensaje registar(Mensaje mensaje){return mensajeRepository.save(mensaje);}
    public List<Mensaje> listado(){
        return mensajeRepository.findAll();
    }
    public Mensaje actualizar(Mensaje mensaje) throws Exception {
        mensajeRepository.findById(mensaje.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return mensajeRepository.save(mensaje);
    }
    public Mensaje eliminar(Integer codigo) throws Exception{
        Mensaje mensaje = mensajeRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        mensajeRepository.delete(mensaje);
        return mensaje;
    }
}
