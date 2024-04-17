package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table (name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombreUsuario", length = 50, nullable = false )
    private String nombreUsuario;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "correo", length = 50, nullable = false )
    private String correo;
    @Column(name = "contrasenia", length = 20, nullable = false )
    private String contrasenia;
    @ManyToOne(targetEntity = Rol.class)
    @JoinColumn(name = "rol_id")
    private Rol rol;



}
