package co.emp.prisig.bean;

import co.emp.prisig.entidades.*;
import co.emp.prisig.servicios.AdminServicio;
import co.emp.prisig.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Component
@ViewScoped
public class InmuebleArriendoBean implements Serializable{

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private List<InmArriendo> inmArriendos;

    @Getter @Setter
    private List<InmArriendo> inmArriendosSeleccionados;

    @Setter @Getter
    private InmArriendo inmArriendo;

    private Map<String, String> imagenesInterior;

    private Map<String, String> imagenesPrincipales;

    @Getter @Setter
    private List<EstadoInmueble> estadoInmuebles;

    @Getter @Setter
    private List<TipoInmueble> tipoInmuebles;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Antiguedad> antiguedades;

    private boolean editar;

    @PostConstruct
    public void init(){
        inmArriendo = new InmArriendo();
        inmArriendos = adminServicio.listarInmArriendo();
        ciudades = adminServicio.listarCiudades();
        inmArriendosSeleccionados = new ArrayList<>();
        imagenesInterior = new HashMap<>();
        imagenesPrincipales = new HashMap<>();
        estadoInmuebles = Arrays.asList(EstadoInmueble.values());
        tipoInmuebles = Arrays.asList(TipoInmueble.values());
        antiguedades = Arrays.asList(Antiguedad.values());
    }

    public void crearInmuebleArriendo() {

        try {
            if (!imagenesInterior.isEmpty() && !editar && !imagenesPrincipales.isEmpty()) {

                inmArriendo.setImagenesPrincipales(imagenesPrincipales);
                inmArriendo.setImagenesInterior(imagenesInterior);
                inmArriendo.setFechaPublicacion(LocalDate.now());

                InmArriendo registro = adminServicio.crearInmArriendo(inmArriendo);
                inmArriendos.add(inmArriendo);

                inmArriendo = new InmArriendo();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inmuble creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminServicio.actualizarInmArriendo(inmArriendo);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Inmueble actualizado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void eliminarInmArriendo(){
        try{
            for (InmArriendo i : inmArriendosSeleccionados){
                adminServicio.eliminarInmArriendo(i.getCodigo());
                inmArriendos.remove(i);
                cloudinaryServicio.eliminarImagen(i.getPublicIdImagenPrincipal());
            }
            inmArriendosSeleccionados.clear();
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "inmueblesArriendo");
            imagenesInterior.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagenPrincipal(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "imagenesPrincipalesArriendo");
            imagenesPrincipales.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }

    public void crearInmuebleDialogo(){
        this.inmArriendo = new InmArriendo();
        editar=false;
    }

    public String getMensajeBorrar(){
        if(inmArriendosSeleccionados.isEmpty()){
            return "Borrar";
        }else {
            return inmArriendosSeleccionados.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " + inmArriendosSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Inmueble";
        }
        return "Crear Inmueble";
    }

    public void seleccionarInmArriendo(InmArriendo inmArriendoSeleccionado){
        this.inmArriendo = inmArriendoSeleccionado;
        editar = true;
    }

}
