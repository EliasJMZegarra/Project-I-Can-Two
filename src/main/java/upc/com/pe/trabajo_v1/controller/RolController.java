package upc.com.pe.trabajo_v1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.com.pe.trabajo_v1.dtos.RolDTO;
import upc.com.pe.trabajo_v1.dtos.UsuarioDTO;
import upc.com.pe.trabajo_v1.entities.Rol;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.service.RolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RolController {
    @Autowired
    public RolService rolService;

    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> obtenerRoles(){
        List<Rol> list = rolService.listado();
        List<RolDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<RolDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/rol")
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO rolDTO) {
        Rol rol;
        try {
            rol = convertToEntity(rolDTO);
            rolDTO = convertToDto(rolService.registrar(rol));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<RolDTO>(rolDTO, HttpStatus.OK);
    }

    @DeleteMapping("/rol/{codigo}")
    public ResponseEntity<RolDTO> borrarRol(@PathVariable(value = "codigo") Integer codigo){
        Rol rol;
        RolDTO rolDTO;
        try {
            rol = rolService.eliminarRol(codigo);

            rolDTO = convertToDto(rol);
            return new ResponseEntity<RolDTO>(rolDTO, HttpStatus.OK);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }

    private RolDTO convertToDto(Rol rol) {
        ModelMapper modelMapper = new ModelMapper();
        RolDTO rolDTO = modelMapper.map(rol, RolDTO.class);
        return rolDTO;
    }

    private Rol convertToEntity(RolDTO rolDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Rol post = modelMapper.map(rolDTO, Rol.class);
        return post;
    }

    private List<RolDTO> convertToListDto(List<Rol> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
