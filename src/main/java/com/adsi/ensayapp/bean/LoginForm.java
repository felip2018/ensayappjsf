package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class LoginForm implements Serializable {

    Logger log = LogManager.getRootLogger();

    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        String urlRedireccion = null;
        Usuario usr;
        try {
            usr = usuarioEJB.iniciarSesion(usuario);
            if (usr != null) {
                if (usr.getEstadoRegistro().equals("Activo")) {
                    if (usr.getValidacionCuenta() == 1) {
                        
                        // Almacenar en la sesion de JSF
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usr);
                        
                        switch (usr.getIdPerfil()) {
                            case 1:
                                urlRedireccion = "views/admin/AdminInicio?faces-redirect=true";
                                break;
                            case 2:
                                urlRedireccion = "views/auxiliary/AuxiliaryInicio?faces-redirect=true";
                                break;
                            case 3:
                                urlRedireccion = "views/musician/MusicianInicio?faces-redirect=true";
                                break;
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Aviso",
                                        "La cuenta de usuario no ha sido activada."));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Aviso",
                                        "La cuenta de usuario se encuentra en estado: "+usr.getEstadoRegistro()));
                    usr = new Usuario();
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Aviso",
                                "El usuario ingresado es incorrecto, verifique!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }

        return urlRedireccion;
    }
    
    public void verificarSesion(){
        String urlRedireccion = null;
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            usuario = u;
            if (u == null) {
                context.getExternalContext().redirect("../../permisos.xhtml?faces-redirect=true");                
            }
        } catch (IOException e) {
            log.info("Excepcion::"+e.getMessage());
        }
    }
    
    public void cerrarSesion(){
        try {
            log.info("Cerrar sesion");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().clear();
            context.getExternalContext().redirect("../../login.xhtml?faces-redirect=true");
        } catch (IOException e) {
            log.info("Excepcion::"+e.getMessage());
        }
    }
}
