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
    private String nombreUsuario;
    private int edad;
    private String correo;
    private String contrasenia;
    private Boolean enabled;
    private List<Rol> rol;
}
