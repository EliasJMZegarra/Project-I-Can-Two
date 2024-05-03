package upc.com.pe.trabajo_v1.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.repository.RolRepository;
import upc.com.pe.trabajo_v1.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService implements UsuarioServiceInterface {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public void registrar(Usuario usuario) {usuarioRepository.save(usuario);}

    //public Usuario buscar(Integer codigo) throws Exception{
      //  return usuarioRepository.findById(codigo).
        //        orElseThrow(() -> new Exception("No se encontró entidad"));
    //}
    @Override
    public Integer buscar(String username){
        int respuesta = usuarioRepository.buscarUsuarioporNombre(username);
        return respuesta;
    }

    @Override
    public List<Usuario> listado(){
        return usuarioRepository.findAll();
    }

    /**
     * @param usuario_id De un usuario existente
     * @param rol_id  De un usuario existente
     * @return 1 exito
     */

    @Override
    public Integer insertUserRol(Integer usuario_id, Integer rol_id){
        Integer result = 0;
        usuarioRepository.insertUserRol(usuario_id, rol_id);
        return 1;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws Exception {
        usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario eliminarUsuario(Integer codigo) throws Exception{
        Usuario usuario = usuarioRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        usuarioRepository.delete(usuario);
        return usuario;
    }
}
