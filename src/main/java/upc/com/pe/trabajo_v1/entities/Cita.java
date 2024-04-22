package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "motivo",length = 200, nullable = false)
    private String motivo;
    @Column(name = "fecha",nullable = false)
    private Date fecha;
    @Column(name = "horario", nullable = false)
    private Time horario;
    @Column(name = "pago", nullable = false)
    private int pago;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(targetEntity = Especialista.class)
    @JoinColumn(name = "especialista_id")
    private Especialista especialista;
}
