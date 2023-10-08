package co.emp.prisig.bean;

import co.emp.prisig.entidades.Administrador;
import co.emp.prisig.entidades.Ciudad;
import co.emp.prisig.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CiudadBean implements Serializable {

    @Autowired
    AdminServicio adminServicio;

    @Getter @Setter
    private Ciudad ciudad;


    @Setter @Getter
    private List<Ciudad> ciudades;

    @Setter @Getter
    private List<Ciudad> ciudadesSeleccionadas;

    private Boolean editar;

    @PostConstruct
    public void init(){
        ciudad = new Ciudad();
        ciudades = adminServicio.listarCiudades();
        ciudadesSeleccionadas = new ArrayList<>();
        editar = false;
    }

    public void crearCiudad(){
        try {
            if (!editar) {
                Ciudad creado = adminServicio.crearCiudad(ciudad);
                ciudades.add(creado);

                ciudad = new Ciudad();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Ciudad creada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminServicio.actualizarCiudad(ciudad);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }


    public void eliminarCiudad(){
        try{
            for(Ciudad c : ciudadesSeleccionadas) {
                adminServicio.eliminarCiudad(c.getCodigo());
                ciudades.remove(c);
            }
            ciudadesSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(ciudadesSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return ciudadesSeleccionadas.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " +  ciudadesSeleccionadas.size() +  " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Editar ciudad" : "Crear ciudad";
    }

    public void seleccionarCiudad(Ciudad ciudadSeleccionada) {
        this.ciudad = ciudadSeleccionada;
        editar = true;
    }

    public void crearCiudadDialogo() {
        this.ciudad = new Ciudad();
        editar = false;
    }
}
