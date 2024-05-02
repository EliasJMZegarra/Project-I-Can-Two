package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table (name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;
    @Column(name = "nombreUsuario", length = 50, nullable = false )
    private String nombreUsuario;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "correo", length = 50, nullable = false )
    private String correo;
    @Column(name = "contrasenia", length = 200, nullable = false )
    private String contrasenia;
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "usario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;



}
