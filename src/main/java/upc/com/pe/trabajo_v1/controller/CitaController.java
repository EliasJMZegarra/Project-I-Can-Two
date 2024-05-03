package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.CitaDTO;
import upc.com.pe.trabajo_v1.entities.Cita;
import upc.com.pe.trabajo_v1.service.CitaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    public CitaService service;

    @GetMapping("/listar")
    public ResponseEntity<List<CitaDTO>> obtenerListadoCitas(){
        List<Cita> list = service.listado();
        List<CitaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<CitaDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<CitaDTO> RegistarCita(@RequestBody CitaDTO citaDTO) {
        Cita cita;
        try {
            cita = convertToEntity(citaDTO);
            citaDTO = convertToDto(service.registar(cita));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<CitaDTO> actualizarCita(@RequestBody CitaDTO reservaDetalle) {
        CitaDTO citaDTO;
        Cita cita;
        try {
            cita = convertToEntity(reservaDetalle);

            cita = service.actualizar(cita);

            citaDTO = convertToDto(cita);
            return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/borrar/{codigo}")
    public ResponseEntity<CitaDTO> elimanarCita(@PathVariable(value = "codigo") Integer codigo){
        Cita cita;
        CitaDTO citaDTO;
        try {
            cita = service.eliminar(codigo);
            citaDTO = convertToDto(cita);
            return new ResponseEntity<CitaDTO>(citaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private CitaDTO convertToDto(Cita cita) {
        ModelMapper modelMapper = new ModelMapper();
        CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class);
        return citaDTO;
    }

    private Cita convertToEntity(CitaDTO citaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Cita post = modelMapper.map(citaDTO, Cita.class);
        return post;
    }

    private List<CitaDTO> convertToListDto(List<Cita> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
