package co.emp.prisig.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class InmVenta extends Inmueble implements Serializable {

    @Column(nullable = false, length = 50)
    private String codigoCatastral;

    @Positive
    @Column(nullable = false)
    private Float areaLote;

    @ManyToOne
    private Ciudad ciudad;

    @Builder
    public InmVenta(Float areaConstruida, String niveles, Antiguedad antiguedad, String direccion, String barrio,
                    Integer estrato, Integer bano, Double valor, Integer habitacion, String estadoConservacion,
                    String descripcion, EstadoInmueble estadoInmueble,  Map<String, String> imagenesPrincipales,
                    Map<String, String> imagenesInterior, TipoInmueble tipoInmueble, Double LAT, Double LNG,
                    String codigoCatastral, Float areaLote, Ciudad ciudad, String fichaTecnica) {
        super(areaConstruida, niveles, antiguedad, direccion, barrio, estrato, bano, valor, habitacion, descripcion,
                estadoInmueble, imagenesPrincipales, imagenesInterior, tipoInmueble, LAT, LNG, fichaTecnica);
        this.codigoCatastral = codigoCatastral;
        this.areaLote = areaLote;
        this.ciudad = ciudad;
    }
}
