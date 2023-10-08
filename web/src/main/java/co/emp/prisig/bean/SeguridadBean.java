package co.emp.prisig.bean;

import co.emp.prisig.entidades.Administrador;
import co.emp.prisig.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String correo, password;

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private String tipoSesion;

    @PostConstruct
    public void init(){
        autenticado = false;
    }

    public String iniciarSesionAdmin(){

        if (!correo.isEmpty() && !password.isEmpty()){
            try {
                administrador = adminServicio.iniciarSesion(correo, password);
                autenticado = true;
                tipoSesion = "admin";
                return "/index_admin?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","El correo y la contraseña son necesarios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index_admin?faces-redirect=true";
    }
}
