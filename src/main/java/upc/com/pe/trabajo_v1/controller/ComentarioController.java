package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.ComentarioDTO;
import upc.com.pe.trabajo_v1.entities.Comentario;
import upc.com.pe.trabajo_v1.service.ComentarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentario(){
        List<Comentario> list = comentarioService.listado();
        List<ComentarioDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<ComentarioDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/comentario")
    public ResponseEntity<ComentarioDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario;
        try {
            comentario = convertToEntity(comentarioDTO);
            comentarioDTO = convertToDto(comentarioService.registrar(comentario));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<ComentarioDTO>(comentarioDTO, HttpStatus.OK);
    }

    @PutMapping("/comentario")
    public ResponseEntity<ComentarioDTO> actualizarComentario(@RequestBody ComentarioDTO comentarioDetalle) {
        ComentarioDTO comentarioDTO;
        Comentario comentario;
        try {
            comentario = convertToEntity(comentarioDetalle);

            comentario = comentarioService.actualizarComentario(comentario);

            comentarioDTO = convertToDto(comentario);
            return new ResponseEntity<ComentarioDTO>(comentarioDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/comentario/{codigo}")
    public ResponseEntity<ComentarioDTO> borrarUsuario(@PathVariable(value = "codigo") Integer codigo){
        Comentario comentario;
        ComentarioDTO comentarioDTO;
        try {
            comentario = comentarioService.eliminarComentario(codigo);

            comentarioDTO = convertToDto(comentario);
            return new ResponseEntity<ComentarioDTO>(comentarioDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private ComentarioDTO convertToDto(Comentario comentario) {
        ModelMapper modelMapper = new ModelMapper();
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }

    private Comentario convertToEntity(ComentarioDTO comentarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Comentario post = modelMapper.map(comentarioDTO, Comentario.class);
        return post;
    }

    private List<ComentarioDTO> convertToListDto(List<Comentario> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
