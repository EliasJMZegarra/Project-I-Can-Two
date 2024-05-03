package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.UsuarioDTO;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    public UsuarioService service;
    @Autowired
    private PasswordEncoder bcrypt;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        List<Usuario> list = service.listado();
        List<UsuarioDTO> listDto = convertToListDto(list);
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<Integer> registarUsuario(@RequestBody Usuario user){
       if(service.buscar(user.getUsername())==0){
           String bcryptPassword = bcrypt.encode(user.getPassword());
           user.setPassword(bcryptPassword);
           service.registrar(user);
           return new ResponseEntity<>(1, HttpStatus.OK);
       }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registrar/{usuario_id}/{rol_id}")
    public ResponseEntity<Integer> CrearUsuariRol(@PathVariable("usuario_id") Integer usuario_id,
                                                  @PathVariable("rol_id") Integer rol_id){
        return new ResponseEntity<>(service.insertUserRol(usuario_id, rol_id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDetalle) {
        UsuarioDTO usuarioDTO;
        Usuario usuario;
        try {
            usuario = convertToEntity(usuarioDetalle);

            usuario = service.actualizarUsuario(usuario);

            usuarioDTO = convertToDto(usuario);
            return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<UsuarioDTO> borrarUsuario(@PathVariable(value = "codigo") Integer codigo){
        Usuario usuario;
        UsuarioDTO usuarioDTO;
        try {
            usuario = service.eliminarUsuario(codigo);

            usuarioDTO = convertToDto(usuario);
            return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private UsuarioDTO convertToDto(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario post = modelMapper.map(usuarioDTO, Usuario.class);
        return post;
    }

    private List<UsuarioDTO> convertToListDto(List<Usuario> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
