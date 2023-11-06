package co.emp.softwareII.bean;

import co.emp.softwareII.entidades.*;
import co.emp.softwareII.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class InmuebleBean implements Serializable {//Bean para consultas de los inmuebles

    @Autowired
    AdminServicio adminServicio;

    @Setter @Getter
    private List<Ciudad> ciudades;

    @Setter @Getter
    private InmArriendo inmArriendo;

    @Setter @Getter
    private InmVenta inmVenta;

    @Getter @Setter
    private List<TipoInmueble> tipoInmuebles;

    @Getter @Setter
    private TipoInmueble tipoInmueble;

    @Getter @Setter
    private List<Antiguedad> antiguedades;

    @Getter @Setter
    private Antiguedad antiguedad;

    @Getter @Setter
    private List<InmArriendo> inmArriendos;

    @Getter @Setter
    private List<InmVenta> inmVentas;

    @Getter @Setter
    private Ciudad ciudad;


    //Bean para consultas de los inmuebles
    @PostConstruct
    public void init(){
        inmArriendo = new InmArriendo();
        inmVenta = new InmVenta();
        ciudades = adminServicio.listarCiudades();
        inmVentas = adminServicio.listarInmVenta();
        inmArriendos = adminServicio.listarInmArriendo();
        antiguedades = Arrays.asList(Antiguedad.values());
        tipoInmuebles = Arrays.asList(TipoInmueble.values());
    }

    public void elegirCiudad() {
        if (ciudad != null){
            inmVentas = adminServicio.listarInmuebleVentaCiudad(ciudad.getCodigo());
            inmArriendos = adminServicio.listarInmuebleArriendoCiudad(ciudad.getCodigo());
        }
    }

    public void elegirAntiguedad() {
        if (antiguedad != null){
            inmVentas = adminServicio.listarInmueblesVentaAntiguedad(antiguedad);
            inmArriendos = adminServicio.listarInmuebleArriendoAntiguedad(antiguedad);
        }
    }

    public void elegirTipoInmueble() {
        inmVentas = adminServicio.listarInmueblesVentaTipoInmueble(tipoInmueble);
        inmArriendos = adminServicio.listarInmuebleArriendoTipoInmueble(tipoInmueble);
    }

}
