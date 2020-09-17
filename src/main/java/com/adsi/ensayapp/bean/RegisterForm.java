package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Perfil;
import com.adsi.ensayapp.model.TipoDocumento;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class RegisterForm implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Usuario usuario;
    private Perfil perfil;
    private TipoDocumento tipoDocumento;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        usuario.setIdPerfil(3);
        usuario.setCodigoValidacion("CODE123");
        usuario.setValidacionCuenta(0);
        usuario.setFechaRegistro(new Date());
        usuario.setEstadoRegistro("Activo");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void registrarUsuario(){
        log.info("---REGISTRO DE USUARIOS---");
        try {
            usuarioEJB.create(usuario);
        } catch (Exception e) {
            throw e;
        }
    }
}
