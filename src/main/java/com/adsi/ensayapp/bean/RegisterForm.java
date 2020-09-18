package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.UserValidationResponseDTO;
import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Perfil;
import com.adsi.ensayapp.model.TipoDocumento;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
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
public class RegisterForm implements Serializable {

    Logger log = LogManager.getRootLogger();

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private Usuario usuario;

    private boolean validacionUsuario;
    private String respuesta;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuario.setIdPerfil(3);
        usuario.setCodigoValidacion(generateRandomString(100));
        usuario.setValidacionCuenta(0);
        usuario.setFechaRegistro(new Date());
        usuario.setEstadoRegistro("Activo");
        

        validacionUsuario = false;
        respuesta = "";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarUsuario() {
        log.info("---REGISTRO DE USUARIOS---");
        try {
            //usuarioEJB.create(usuario);
            UserValidationResponseDTO validacion = usuarioEJB.validacionUsuario(usuario);
            
            log.info("Usuarios: "+validacion.getCant()+", Mensaje de validacion: "+validacion.getMensaje());
            
        } catch (Exception e) {
            throw e;
        }
    }

    public String generateRandomString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        return randomString;
    }
}
