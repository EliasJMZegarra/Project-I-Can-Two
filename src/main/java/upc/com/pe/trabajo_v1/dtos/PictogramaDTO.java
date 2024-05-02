package upc.com.pe.trabajo_v1.dtos;

import lombok.Data;

@Data
public class PictogramaDTO {
    private int id;
    private String filename;
    private String descripcion;
    private String categoria;
    //private byte[] imagenData;
}
