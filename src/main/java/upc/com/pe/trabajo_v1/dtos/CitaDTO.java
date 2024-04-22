package upc.com.pe.trabajo_v1.dtos;

import lombok.Data;
import upc.com.pe.trabajo_v1.entities.Especialista;
import upc.com.pe.trabajo_v1.entities.Usuario;

import java.sql.Time;
import java.util.Date;
@Data
public class CitaDTO {
    private int id;
    private String motivo;
    private Date fecha;
    private Time horario;
    private int pago;
    private Usuario usuario;
    private Especialista especialista;
}
