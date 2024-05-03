package upc.com.pe.trabajo_v1.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import upc.com.pe.trabajo_v1.entities.Rol;

import java.util.List;

@Setter
@Getter
public class UsuarioDTO {
    private int id;
    private String username;
    private int edad;
    private String correo;
    private String password;
    private Boolean enabled;
    private List<Rol> rol;
}
