package upc.com.pe.trabajo_v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pictograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imagen", length = 200, nullable = false)
    private String imagen;
    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;
    @Column(name = "categoria",length = 50, nullable = false)
    private String categoria;
}
