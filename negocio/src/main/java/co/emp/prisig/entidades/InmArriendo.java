package co.emp.prisig.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class InmArriendo extends Inmueble implements Serializable {

    @Column(nullable = false, length = 50)
    private String matriculaInmobiliaria;

    @Column(nullable = false, length = 20)
    private String localidad;

    @ManyToOne
    private Ciudad ciudad;

    @Builder
    public InmArriendo(Float areaConstruida, String niveles, Antiguedad antiguedad, String direccion, String barrio,
                       Integer estrato, Integer bano, Double valor, Integer habitacion, String estadoConservacion,
                       String descripcion, EstadoInmueble estadoInmueble,  Map<String, String> imagenesPrincipales,
                       Map<String, String> imagenesInterior, TipoInmueble tipoInmueble, Double LAT, Double LNG,
                       String matriculaInmobiliaria, String localidad, Ciudad ciudad, String fichaTecnica) {
        super(areaConstruida, niveles, antiguedad, direccion, barrio, estrato, bano, valor, habitacion, descripcion,
                estadoInmueble, imagenesPrincipales, imagenesInterior, tipoInmueble, LAT, LNG, fichaTecnica);
        this.matriculaInmobiliaria = matriculaInmobiliaria;
        this.localidad = localidad;
        this.ciudad = ciudad;
    }
}
