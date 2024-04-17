package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Comentario;
import upc.com.pe.trabajo_v1.repository.ComentarioRepository;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Transactional
    public Comentario registrar(Comentario comentario){return comentarioRepository.save(comentario);}

    public List<Comentario> listado(){
        return comentarioRepository.findAll();
    }

    public Comentario actualizarComentario(Comentario comentario) throws Exception {
        comentarioRepository.findById(comentario.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return comentarioRepository.save(comentario);
    }

    public Comentario eliminarComentario(Integer codigo) throws Exception{
        Comentario comentario = comentarioRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        comentarioRepository.delete(comentario);
        return comentario;
    }
}
