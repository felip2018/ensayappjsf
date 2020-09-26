package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.EmailMessageDTO;
import com.adsi.ensayapp.dto.UserValidationResponseDTO;
import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Usuario;
import com.adsi.ensayapp.utilities.SendEmail;
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
    private SendEmail sendEmail;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private Usuario usuario;

    private boolean validacionUsuario;
    private String respuesta;

    @PostConstruct
    public void init() {
        sendEmail = new SendEmail();
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
            
            UserValidationResponseDTO validacion = usuarioEJB.validacionUsuario(usuario);
            if (validacion.getCant() == 0){
                usuarioEJB.create(usuario);
                
                EmailMessageDTO emailMessageDto = new EmailMessageDTO();
                emailMessageDto.setTo(usuario.getCorreo());
                emailMessageDto.setSubject("Ensayapp: Registro exitoso");
                String body = "<h1>Bienvenido "+usuario.getNombre()+"</h1>";
                body += "<hr/>";
                body += "<p>Has completado el registro en nuestro sistema de forma exitosa, haz clic en el siguiente enlace para activar tu cuenta:</p>";
                body += "<div class='containerActivationButton'>";
                body += "<a class='btn-activation' href='http://localhost:8080/ensayappjsf/faces/accountValidation.xhtml?code="+usuario.getCodigoValidacion()+"' target='_blank'>Activar Cuenta</a>";
                body += "</div>";
                emailMessageDto.setBody(body);
                
                sendEmail.sendEmailMessage(emailMessageDto);
                
                this.respuesta = "Has completado el registro! por favor verifica tu correo electrónico para validar la cuenta.";
            }else{
                log.info("Usuarios: "+validacion.getCant()+", Mensaje de validacion: "+validacion.getMensaje());
                this.respuesta = validacion.getMensaje();
            }
            
        } catch (Exception e) {
            throw e;
        }
    }

    public String generateRandomString(int length) {
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length); 
  
        for (int i = 0; i < length; i++) { 
            // generar un numero aleatorio entre el rango de la cadena de caracteres
            int index = (int)(AlphaNumericString.length()* Math.random()); 
            // agregar el caracter del indice elegido a la cadena
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    }

    public boolean isValidacionUsuario() {
        return validacionUsuario;
    }

    public void setValidacionUsuario(boolean validacionUsuario) {
        this.validacionUsuario = validacionUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
