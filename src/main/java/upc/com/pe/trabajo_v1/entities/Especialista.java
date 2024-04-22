package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Especialista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "especialidad", length = 50, nullable = false)
    private String especialidad;
    @Column(name = "telefono", nullable = false)
    private int telefono;
   
}
