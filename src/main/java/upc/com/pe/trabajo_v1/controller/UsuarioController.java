package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.UsuarioDTO;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    public UsuarioService service;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        List<Usuario> list = service.listado();
        List<UsuarioDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<UsuarioDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario;
        try {
            usuario = convertToEntity(usuarioDTO);
            usuarioDTO = convertToDto(service.registrar(usuario));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    @PutMapping("/usuario")
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

    @DeleteMapping("/usuario/{codigo}")
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
