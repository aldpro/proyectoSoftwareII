package co.emp.prisig.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<InmArriendo> inmueblesArriendos;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<InmVenta> inmueblesVentas;

    @Builder
    public Ciudad(String nombre) {
        this.nombre = nombre;
        this.inmueblesArriendos = new ArrayList<>();
        this.inmueblesVentas = new ArrayList<>();
    }
}
