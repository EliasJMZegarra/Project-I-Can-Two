package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.MensajeDTO;
import upc.com.pe.trabajo_v1.entities.Mensaje;
import upc.com.pe.trabajo_v1.service.MensajeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MensajeController {
    @Autowired
    public MensajeService service;

    @GetMapping("/mensajes")
    public ResponseEntity<List<MensajeDTO>> obtenerMensaje(){
        List<Mensaje> list = service.listado();
        List<MensajeDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<MensajeDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/mensaje")
    public ResponseEntity<MensajeDTO> RegistarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        Mensaje mensaje;
        try {
            mensaje = convertToEntity(mensajeDTO);
            mensajeDTO = convertToDto(service.registar(mensaje));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<MensajeDTO>(mensajeDTO, HttpStatus.OK);
    }

    @PutMapping("/mensaje")
    public ResponseEntity<MensajeDTO> actualizar(@RequestBody MensajeDTO mensajeDetalle) {
        MensajeDTO mensajeDTO;
        Mensaje mensaje;
        try {
            mensaje = convertToEntity(mensajeDetalle);

            mensaje = service.actualizar(mensaje);

            mensajeDTO = convertToDto(mensaje);
            return new ResponseEntity<MensajeDTO>(mensajeDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/mensaje/{codigo}")
    public ResponseEntity<MensajeDTO> borrarMensaje(@PathVariable(value = "codigo") Integer codigo){
        Mensaje mensaje;
        MensajeDTO mensajeDTO;
        try {
            mensaje = service.eliminar(codigo);
            mensajeDTO = convertToDto(mensaje);
            return new ResponseEntity<MensajeDTO>(mensajeDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private MensajeDTO convertToDto(Mensaje mensaje) {
        ModelMapper modelMapper = new ModelMapper();
        MensajeDTO mensajeDTO = modelMapper.map(mensaje, MensajeDTO.class);
        return mensajeDTO;
    }

    private Mensaje convertToEntity(MensajeDTO mensajeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Mensaje post = modelMapper.map(mensajeDTO, Mensaje.class);
        return post;
    }

    private List<MensajeDTO> convertToListDto(List<Mensaje> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
