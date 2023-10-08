package co.emp.softwareII.bean;

import co.emp.softwareII.entidades.InmVenta;
import co.emp.softwareII.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.ResponsiveOption;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class DetalleInmuebleVentaBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Value("#{param['inmueble_id']}")
    private String codigoInmueble;

    @Getter @Setter
    private InmVenta inmVenta;

    @Getter @Setter
    private List<ResponsiveOption> responsiveOptions1;

    @Getter @Setter
    private List<String> imagenesVenta;

    @Getter @Setter
    private int activeIndex = 0;

    @Getter @Setter
    private MapModel simpleModel;

    @Getter @Setter
    private String ubicacion;

    @PostConstruct
    public void init(){

        try {
            if (codigoInmueble != null && !codigoInmueble.isEmpty()){
                inmVenta = adminServicio.obtenerInmVenta(Integer.parseInt(codigoInmueble));
                simpleModel = new DefaultMapModel();
                Marker marker = new Marker(new LatLng(inmVenta.getLAT(), inmVenta.getLNG()), inmVenta.getDireccion(), 3L, "/resources/img/prisig_icon_u.png");
                simpleModel.addOverlay(marker);
                String lat = String.valueOf(getInmVenta().getLAT());
                String lng = String.valueOf(getInmVenta().getLNG());
                ubicacion = lat+","+lng;
                imagenesVenta = inmVenta.getImagenes();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        responsiveOptions1 = new ArrayList<>();
        responsiveOptions1.add(new ResponsiveOption("1024px", 5));
        responsiveOptions1.add(new ResponsiveOption("768px", 3));
        responsiveOptions1.add(new ResponsiveOption("560px", 1));
    }

    public void changeActiveIndex() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.activeIndex = Integer.valueOf(params.get("index"));
    }

}
