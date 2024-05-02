package upc.com.pe.trabajo_v1.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDTO {
    private int id;
    private String tipoRol;

}
