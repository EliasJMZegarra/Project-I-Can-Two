package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.TestimonioDTO;
import upc.com.pe.trabajo_v1.entities.Testimonio;
import upc.com.pe.trabajo_v1.service.TestimonioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestimonioController {
    @Autowired
    public TestimonioService service;

    @GetMapping("/testimonios")
    public ResponseEntity<List<TestimonioDTO>> obtenerTestimonio(){
        List<Testimonio> list = service.listado();
        List<TestimonioDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<TestimonioDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/testimonio")
    public ResponseEntity<TestimonioDTO> crearTestimonio(@RequestBody TestimonioDTO testimonioDTO) {
        Testimonio testimonio;
        try {
            testimonio = convertToEntity(testimonioDTO);
            testimonioDTO = convertToDto(service.registar(testimonio));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<TestimonioDTO>(testimonioDTO, HttpStatus.OK);
    }

    @PutMapping("/testimonio")
    public ResponseEntity<TestimonioDTO> actualizarTestimonio(@RequestBody TestimonioDTO testimonioDetalle) {
        TestimonioDTO testimonioDTO;
        Testimonio testimonio;
        try {
            testimonio = convertToEntity(testimonioDetalle);

            testimonio = service.actualizarTestimonio(testimonio);

            testimonioDTO = convertToDto(testimonio);
            return new ResponseEntity<TestimonioDTO>(testimonioDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/testimonio/{codigo}")
    public ResponseEntity<TestimonioDTO> borrarTestimonio(@PathVariable(value = "codigo") Integer codigo){
        Testimonio testimonio;
        TestimonioDTO testimonioDTO;
        try {
            testimonio = service.eliminarTestionio(codigo);
            testimonioDTO = convertToDto(testimonio);
            return new ResponseEntity<TestimonioDTO>(testimonioDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private TestimonioDTO convertToDto(Testimonio testimonio) {
        ModelMapper modelMapper = new ModelMapper();
        TestimonioDTO testimonioDTO = modelMapper.map(testimonio, TestimonioDTO.class);
        return testimonioDTO;
    }

    private Testimonio convertToEntity(TestimonioDTO testimonioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Testimonio post = modelMapper.map(testimonioDTO, Testimonio.class);
        return post;
    }

    private List<TestimonioDTO> convertToListDto(List<Testimonio> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
