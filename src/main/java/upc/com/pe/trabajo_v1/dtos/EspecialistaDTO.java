package upc.com.pe.trabajo_v1.dtos;

import lombok.Data;
import upc.com.pe.trabajo_v1.entities.Clinica;
@Data
public class EspecialistaDTO {
    private int id;
    private String nombre;
    private int edad;
    private String especialidad;
    private int telefono;
    private Clinica clinica;
}
