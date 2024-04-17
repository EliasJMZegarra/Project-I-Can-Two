package upc.com.pe.trabajo_v1.dtos;

import lombok.Data;
import upc.com.pe.trabajo_v1.entities.Usuario;

@Data
public class TestimonioDTO {
    private int id;
    private String descripcion;
    private Usuario usuario;
}
