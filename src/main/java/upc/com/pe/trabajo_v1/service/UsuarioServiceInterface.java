package upc.com.pe.trabajo_v1.service;

import org.springframework.transaction.annotation.Transactional;
import upc.com.pe.trabajo_v1.entities.Usuario;

import java.util.List;

public interface UsuarioServiceInterface {
    @Transactional
    void registrar(Usuario usuario);

    //public Usuario buscar(Integer codigo) throws Exception{
    //  return usuarioRepository.findById(codigo).
    //        orElseThrow(() -> new Exception("No se encontró entidad"));
    //}
    Integer buscar(String username);

    List<Usuario> listado();

    Integer insertUserRol(Integer usuario_id, Integer rol_id);

    Usuario actualizarUsuario(Usuario usuario) throws Exception;

    Usuario eliminarUsuario(Integer codigo) throws Exception;
}
