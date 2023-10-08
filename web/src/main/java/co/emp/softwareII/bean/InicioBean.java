package co.emp.softwareII.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Setter @Getter
    private ArrayList<String> imagenesCarousel;

    @Setter @Getter
    private ArrayList<String> imagenesCarouselMision;

    @Setter @Getter
    private ArrayList<String> imagenesCarouselVision;

    @Setter @Getter
    private ArrayList<String> imagenesCarouselNosostros;

    @PostConstruct
    public void inicializar(){

        try {
            imagenesCarouselNosostros = new ArrayList<>();
            imagenesCarouselVision = new ArrayList<>();
            imagenesCarouselMision = new ArrayList<>();
            imagenesCarousel = new ArrayList<>();

            imagenesCarousel.add("/resources/img/imagen-carusel.png");
            imagenesCarousel.add("/resources/img/topografia3.jpg");
            imagenesCarousel.add("/resources/img/carusel.jpg");

            imagenesCarouselNosostros.add("/resources/img/nosotros1.JPG");
            imagenesCarouselNosostros.add("/resources/img/nosotros2.JPG");
            imagenesCarouselNosostros.add("/resources/img/nosotros3.JPG");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
