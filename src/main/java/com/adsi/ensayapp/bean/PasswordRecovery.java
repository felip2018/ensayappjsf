package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class PasswordRecovery implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    private Usuario usuario = new Usuario();
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void updatePassword(){
        System.out.println("Codigo: "+usuario.getCodigoValidacion());
        System.out.println("Clave: "+usuario.getClave());
    }
    
}
