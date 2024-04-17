package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.ReservaDTO;
import upc.com.pe.trabajo_v1.entities.Reserva;
import upc.com.pe.trabajo_v1.service.ReservaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservaController {
    @Autowired
    public ReservaService service;

    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaDTO>> obtenerReserva(){
        List<Reserva> list = service.listado();
        List<ReservaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<ReservaDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/reserva")
    public ResponseEntity<ReservaDTO> RegistarReserva(@RequestBody ReservaDTO reservaDTO) {
        Reserva reserva;
        try {
            reserva = convertToEntity(reservaDTO);
            reservaDTO = convertToDto(service.registar(reserva));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<ReservaDTO>(reservaDTO, HttpStatus.OK);
    }

    @PutMapping("/reserva")
    public ResponseEntity<ReservaDTO> actualizar(@RequestBody ReservaDTO reservaDetalle) {
        ReservaDTO reservaDTO;
        Reserva reserva;
        try {
            reserva = convertToEntity(reservaDetalle);

            reserva = service.actualizar(reserva);

            reservaDTO = convertToDto(reserva);
            return new ResponseEntity<ReservaDTO>(reservaDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @DeleteMapping("/reserva/{codigo}")
    public ResponseEntity<ReservaDTO> borrarReserva(@PathVariable(value = "codigo") Integer codigo){
        Reserva reserva;
        ReservaDTO reservaDTO;
        try {
            reserva = service.eliminar(codigo);
            reservaDTO = convertToDto(reserva);
            return new ResponseEntity<ReservaDTO>(reservaDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private ReservaDTO convertToDto(Reserva reserva) {
        ModelMapper modelMapper = new ModelMapper();
        ReservaDTO reservaDTO  = modelMapper.map(reserva, ReservaDTO.class);
        return reservaDTO;
    }

    private Reserva convertToEntity(ReservaDTO reservaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Reserva post = modelMapper.map(reservaDTO, Reserva.class);
        return post;
    }

    private List<ReservaDTO> convertToListDto(List<Reserva> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
