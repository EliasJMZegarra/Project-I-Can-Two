package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.EspecialistaDTO;
import upc.com.pe.trabajo_v1.entities.Especialista;
import upc.com.pe.trabajo_v1.service.EspecialistaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EspecialistaController {
    @Autowired
    public EspecialistaService service;

    @GetMapping("/especialistas")
    public ResponseEntity<List<EspecialistaDTO>> obtenerEspecialista(){
        List<Especialista> list = service.listado();
        List<EspecialistaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<EspecialistaDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/especialista")
    public ResponseEntity<EspecialistaDTO> RegistarEspecialista(@RequestBody EspecialistaDTO especialistaDTO) {
        Especialista especialista;
        try {
            especialista = convertToEntity(especialistaDTO);
            especialistaDTO = convertToDto(service.registar(especialista));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<EspecialistaDTO>(especialistaDTO, HttpStatus.OK);
    }

    @PutMapping("/especialista")
    public ResponseEntity<EspecialistaDTO> actualizar(@RequestBody EspecialistaDTO especialistaDetalle) {
        EspecialistaDTO especialistaDTO;
        Especialista especialista;
        try {
            especialista = convertToEntity(especialistaDetalle);

            especialista = service.actualizar(especialista);

            especialistaDTO = convertToDto(especialista);
            return new ResponseEntity<EspecialistaDTO>(especialistaDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/especialista/{codigo}")
    public ResponseEntity<EspecialistaDTO> borrarEspecialista(@PathVariable(value = "codigo") Integer codigo){
        Especialista especialista;
        EspecialistaDTO especialistaDTO;
        try {
            especialista = service.eliminar(codigo);
            especialistaDTO = convertToDto(especialista);
            return new ResponseEntity<EspecialistaDTO>(especialistaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private EspecialistaDTO convertToDto(Especialista especialista) {
        ModelMapper modelMapper = new ModelMapper();
        EspecialistaDTO especialistaDTO  = modelMapper.map(especialista, EspecialistaDTO.class);
        return especialistaDTO;
    }

    private Especialista convertToEntity(EspecialistaDTO especialistaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Especialista post = modelMapper.map(especialistaDTO, Especialista.class);
        return post;
    }

    private List<EspecialistaDTO> convertToListDto(List<Especialista> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
