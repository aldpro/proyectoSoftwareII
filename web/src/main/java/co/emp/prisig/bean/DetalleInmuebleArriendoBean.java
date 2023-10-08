package co.emp.prisig.bean;

import co.emp.prisig.entidades.InmArriendo;
import co.emp.prisig.servicios.AdminServicio;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class DetalleInmuebleArriendoBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Value("#{param['inmueble_id']}")
    private String codigoInmueble;

    @Getter @Setter
    private InmArriendo inmArriendo;

    @Getter @Setter
    private List<ResponsiveOption> responsiveOptions1;

    @Getter @Setter
    private List<String> imagenesArriendo;

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
                inmArriendo = adminServicio.obtenerInmArriendo(Integer.parseInt(codigoInmueble));
                simpleModel = new DefaultMapModel();
                Marker marker = new Marker(new LatLng(inmArriendo.getLAT(), inmArriendo.getLNG()), inmArriendo.getDireccion(), 3L, "/resources/img/prisig_icon_u.png");
                simpleModel.addOverlay(marker);
                String lat = String.valueOf(getInmArriendo().getLAT());
                String lng = String.valueOf(getInmArriendo().getLNG());
                ubicacion = lat+","+lng;
                imagenesArriendo = inmArriendo.getImagenes();
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

