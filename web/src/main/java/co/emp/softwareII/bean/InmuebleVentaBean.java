package co.emp.softwareII.bean;

import co.emp.softwareII.entidades.*;
import co.emp.softwareII.servicios.AdminServicio;
import co.emp.softwareII.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
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
public class InmuebleVentaBean implements Serializable{

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private List<InmVenta> inmVentas;

    @Getter @Setter
    private List<InmVenta> inmVentasSeleccionados;

    @Setter @Getter
    private InmVenta inmVenta;

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
        inmVenta = new InmVenta();
        inmVentas = adminServicio.listarInmVenta();
        ciudades = adminServicio.listarCiudades();
        inmVentasSeleccionados = new ArrayList<>();
        imagenesInterior = new HashMap<>();
        imagenesPrincipales = new HashMap<>();
        estadoInmuebles = Arrays.asList(EstadoInmueble.values());
        tipoInmuebles = Arrays.asList(TipoInmueble.values());
        antiguedades = Arrays.asList(Antiguedad.values());
    }

    public void crearInmuebleVenta() {

        try {
            if (!imagenesInterior.isEmpty() && !editar) {

                inmVenta.setImagenesPrincipales(imagenesPrincipales);
                inmVenta.setImagenesInterior(imagenesInterior);
                inmVenta.setFechaPublicacion(LocalDate.now());

                InmVenta registro = adminServicio.crearInmVenta(inmVenta);
                inmVentas.add(inmVenta);

                inmVenta = new InmVenta();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inmuble creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminServicio.actualizarInmVenta(inmVenta);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inmueble actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void eliminarInmVenta(){
        try{
            for (InmVenta i : inmVentasSeleccionados ){
                adminServicio.eliminarInmVenta(i.getCodigo());
                inmVentas.remove(i);
                cloudinaryServicio.eliminarImagen(i.getPublicIdImagenPrincipal());
            }
            inmVentasSeleccionados.clear();
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "inmueblesVenta");
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
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "imagenesPrincipalesVenta");
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
        this.inmVenta = new InmVenta();
        editar=false;
    }

    public String getMensajeBorrar(){
        if(inmVentasSeleccionados.isEmpty()){
            return "Borrar";
        }else {
            return inmVentasSeleccionados.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " + inmVentasSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Inmueble";
        }
        return "Crear Inmueble";
    }

    public void seleccionarInmVenta(InmVenta inmVentaSeleccionado){
        this.inmVenta = inmVentaSeleccionado;

        editar = true;
    }
}
