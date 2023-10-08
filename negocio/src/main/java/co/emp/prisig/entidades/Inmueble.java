package co.emp.prisig.entidades;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Inmueble implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Float areaConstruida;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @Column(nullable = false, length = 15)
    private String niveles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Antiguedad antiguedad;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String barrio;

    @Column
    private Integer estrato;

    @Column
    private Integer bano;

    @Column(nullable = false)
    @Positive
    private Double valor;

    @Column
    private Integer habitacion;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInmueble estadoInmueble;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenesPrincipales;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenesInterior;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInmueble tipoInmueble;

    @Column(nullable = false)
    private Double LAT;

    @Column(nullable = false)
    private Double LNG;

    @Column
    @Lob
    private String fichaTecnica;

    public Inmueble(Float areaConstruida, String niveles, Antiguedad antiguedad, String direccion, String barrio,
                    Integer estrato, Integer bano, Double valor, Integer habitacion, String descripcion,
                    EstadoInmueble estadoInmueble, Map<String, String> imagenesPrincipales,
                    Map<String, String> imagenesInterior, TipoInmueble tipoInmueble, Double LAT, Double LNG, String fichaTecnica) {
        this.areaConstruida = areaConstruida;
        this.fechaPublicacion = LocalDate.now();
        this.niveles = niveles;
        this.antiguedad = antiguedad;
        this.direccion = direccion;
        this.barrio = barrio;
        this.estrato = estrato;
        this.bano = bano;
        this.valor = valor;
        this.habitacion = habitacion;
        this.descripcion = descripcion;
        this.estadoInmueble = estadoInmueble;
        this.imagenesPrincipales = imagenesPrincipales;
        this.imagenesInterior = imagenesInterior;
        this.tipoInmueble = tipoInmueble;
        this.LAT = LAT;
        this.LNG = LNG;
        this.fichaTecnica = fichaTecnica;
    }

    public String cambiarFormato(){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
        String valorDecimal = decimalFormat.format(valor);
        return valorDecimal;
    }

    public String getImagenPrincipal(){
        if (!imagenesPrincipales.isEmpty()){
            String primera = imagenesPrincipales.keySet().toArray()[0].toString();
            return imagenesPrincipales.get(primera);
        }
        return "https://res.cloudinary.com/dcdp3aquu/image/upload/v1688573472/prisig/no_image_jx6fds.jpg";
    }

    public String getPublicIdImagenPrincipal() {
        if (!imagenesPrincipales.isEmpty()) {
            String primeraClave = imagenesPrincipales.keySet().toArray(new String[0])[0];
            return primeraClave;
        }
        return null;
    }


    public List<String> getImagenes(){
        if (!imagenesInterior.isEmpty()){
            List<String> imagenes = new ArrayList<>();
            String primera;
            for (int i = 0; i < imagenesInterior.size(); i++){
                 primera = imagenesInterior.keySet().toArray()[i].toString();
                imagenes.add(imagenesInterior.get(primera));
            }
            return imagenes;
        }
        return null;
    }
}
