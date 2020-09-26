package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.EmailMessageDTO;
import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Usuario;
import com.adsi.ensayapp.utilities.SendEmail;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class RecoveryForm implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    private SendEmail sendEmail;
    private Usuario usuario;
    private String respuesta;
    private int id;
    private String code;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        sendEmail = new SendEmail();
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void validarCorreoElectronico(){
        Usuario usr; 
        try {
            usr = usuarioEJB.validarCorreoRecuperacion(usuario);
            if (usr != null) {
                log.info("Se enviará el correo electrónico!");
                EmailMessageDTO emailMessageDto = new EmailMessageDTO();
                emailMessageDto.setTo(usr.getCorreo());
                emailMessageDto.setSubject("Ensayapp: Recuperar contraseña");
                String body = "<h1>Hola "+usr.getNombre()+"</h1>";
                body += "<hr/>";
                body += "<p>Se ha realizaco una solicitud para recuperar la clave de acceso a tu cuenta, da clic en el botón para realizar la recuperación:</p>";
                body += "<div class='containerActivationButton'>";
                body += "<a class='btn-activation' href='http://localhost:8080/ensayappjsf/faces/passwordRecovery.xhtml?id="+usr.getId()+"&code="+usr.getCodigoValidacion()+"' target='_blank'>Recuperar Clave</a>";
                body += "</div>";
                emailMessageDto.setBody(body);
                
                sendEmail.sendEmailMessage(emailMessageDto);
                
                this.respuesta = "Se ha enviado un correo electrónico con los pasos a seguir para restablecer tu clave de acceso.";
            }else{
                this.respuesta = "El correo electrónico ingresado no pertenece a ninguna cuenta registrada, por favor verifique!";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
