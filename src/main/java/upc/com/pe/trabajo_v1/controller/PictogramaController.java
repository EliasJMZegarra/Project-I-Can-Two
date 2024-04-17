package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.PictogramaDTO;
import upc.com.pe.trabajo_v1.entities.Pictograma;
import upc.com.pe.trabajo_v1.service.PictogramaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PictogramaController {
    @Autowired
    public PictogramaService service;

    @GetMapping("/pictogramas")
    public ResponseEntity<List<PictogramaDTO>> obtenerPictograma(){
        List<Pictograma> list = service.listado();
        List<PictogramaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<PictogramaDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/pictograma")
    public ResponseEntity<PictogramaDTO> RegistarPictograma(@RequestBody PictogramaDTO pictogramaDTO) {
        Pictograma pictograma;
        try {
            pictograma = convertToEntity(pictogramaDTO);
            pictogramaDTO = convertToDto(service.registar(pictograma));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<PictogramaDTO>(pictogramaDTO, HttpStatus.OK);
    }

    @PutMapping("/pictograma")
    public ResponseEntity<PictogramaDTO> actualizar(@RequestBody PictogramaDTO pictogramaDetalle) {
        PictogramaDTO pictogramaDTO;
        Pictograma pictograma;
        try {
            pictograma = convertToEntity(pictogramaDetalle);

            pictograma = service.actualizar(pictograma);

            pictogramaDTO = convertToDto(pictograma);
            return new ResponseEntity<PictogramaDTO>(pictogramaDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/pictograma/{codigo}")
    public ResponseEntity<PictogramaDTO> borrarPictograma(@PathVariable(value = "codigo") Integer codigo){
        Pictograma pictograma;
        PictogramaDTO pictogramaDTO;
        try {
            pictograma = service.eliminar(codigo);
            pictogramaDTO = convertToDto(pictograma);
            return new ResponseEntity<PictogramaDTO>(pictogramaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private PictogramaDTO convertToDto(Pictograma pictograma) {
        ModelMapper modelMapper = new ModelMapper();
        PictogramaDTO pictogramaDTO = modelMapper.map(pictograma, PictogramaDTO.class);
        return pictogramaDTO;
    }

    private Pictograma convertToEntity(PictogramaDTO pictogramaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Pictograma post = modelMapper.map(pictogramaDTO, Pictograma.class);
        return post;
    }

    private List<PictogramaDTO> convertToListDto(List<Pictograma> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
