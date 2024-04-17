package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion", length = 200, nullable = false)
    private String descripcion;
    @Column(name = "favoritos", length = 200, nullable = false)
    private String favoritos;
    @ManyToOne(targetEntity = Pictograma.class)
    @JoinColumn(name = "pictograma_id")
    private Pictograma pictograma;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
