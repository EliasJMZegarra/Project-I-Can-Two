package upc.com.pe.trabajo_v1.dtos;

import lombok.Getter;
import lombok.Setter;
import upc.com.pe.trabajo_v1.entities.Usuario;

@Setter
@Getter
public class RolDTO {
    private int id;
    private String tipoRol;
    private Usuario usuario;

}
