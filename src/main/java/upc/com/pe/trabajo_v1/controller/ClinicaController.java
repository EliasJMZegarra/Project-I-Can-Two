package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.ClinicaDTO;
import upc.com.pe.trabajo_v1.entities.Clinica;
import upc.com.pe.trabajo_v1.service.ClinicaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClinicaController {
    @Autowired
    public ClinicaService service;

    @GetMapping("/clinicas")
    public ResponseEntity<List<ClinicaDTO>> obtenerClinica(){
        List<Clinica> list = service.listado();
        List<ClinicaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<ClinicaDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/clinica")
    public ResponseEntity<ClinicaDTO> RegistarClinica(@RequestBody ClinicaDTO clinicaDTO) {
        Clinica clinica;
        try {
            clinica = convertToEntity(clinicaDTO);
            clinicaDTO = convertToDto(service.registar(clinica));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<ClinicaDTO>(clinicaDTO, HttpStatus.OK);
    }

    @PutMapping("/clinica")
    public ResponseEntity<ClinicaDTO> actualizar(@RequestBody ClinicaDTO clinicaDetalle) {
        ClinicaDTO clinicaDTO;
        Clinica clinica;
        try {
            clinica = convertToEntity(clinicaDetalle);

            clinica = service.actualizar(clinica);

            clinicaDTO = convertToDto(clinica);
            return new ResponseEntity<ClinicaDTO>(clinicaDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/clinica/{codigo}")
    public ResponseEntity<ClinicaDTO> borrarClinica(@PathVariable(value = "codigo") Integer codigo){
        Clinica clinica;
        ClinicaDTO clinicaDTO;
        try {
            clinica = service.eliminar(codigo);
            clinicaDTO = convertToDto(clinica);
            return new ResponseEntity<ClinicaDTO>(clinicaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private ClinicaDTO convertToDto(Clinica clinica) {
        ModelMapper modelMapper = new ModelMapper();
        ClinicaDTO clinicaDTO  = modelMapper.map(clinica, ClinicaDTO.class);
        return clinicaDTO;
    }

    private Clinica convertToEntity(ClinicaDTO clinicaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Clinica post = modelMapper.map(clinicaDTO, Clinica.class);
        return post;
    }

    private List<ClinicaDTO> convertToListDto(List<Clinica> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
