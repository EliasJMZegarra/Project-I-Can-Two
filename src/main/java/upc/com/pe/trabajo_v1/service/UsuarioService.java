package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.repository.RolRepository;
import upc.com.pe.trabajo_v1.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional

    public Usuario registrar(Usuario usuario){return usuarioRepository.save(usuario);}

    //public Usuario buscar(Integer codigo) throws Exception{
      //  return usuarioRepository.findById(codigo).
        //        orElseThrow(() -> new Exception("No se encontró entidad"));
    //}
    public Integer buscar(String nombreUsuario){
        int respuesta = usuarioRepository.buscarUsuarioporNombre(nombreUsuario);
        return respuesta;
    }

    public List<Usuario> listado(){
        return usuarioRepository.findAll();
    }

    public Integer insertUserRol(Integer usuario_id, Integer rol_id){
        Integer result = 0;
        usuarioRepository.insertUserRol(usuario_id, rol_id);
        return 1;
    }

    public Usuario actualizarUsuario(Usuario usuario) throws Exception {
        usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return usuarioRepository.save(usuario);
    }

    public Usuario eliminarUsuario(Integer codigo) throws Exception{
        Usuario usuario = usuarioRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        usuarioRepository.delete(usuario);
        return usuario;
    }
}
