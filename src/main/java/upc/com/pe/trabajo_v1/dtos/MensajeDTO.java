package upc.com.pe.trabajo_v1.dtos;

import lombok.Data;
import upc.com.pe.trabajo_v1.entities.Pictograma;
import upc.com.pe.trabajo_v1.entities.Usuario;

@Data
public class MensajeDTO {
    private int id;
    private String descripcion;
    private String favoritos;
    private Pictograma pictograma;
    private Usuario usuario;
}
