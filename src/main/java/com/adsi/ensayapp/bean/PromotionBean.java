
package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.EmailMessageDTO;
import com.adsi.ensayapp.utilities.SendEmail;
import com.adsi.ensayapp.ejb.PromocionFacadeLocal;
import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.Promocion;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class PromotionBean implements Serializable {
    Logger log = LogManager.getRootLogger();
    
    private SendEmail sendEmail;
    
    @EJB
    private PromocionFacadeLocal promocionEJB;
    
    private Promocion promocion;
    
    private List<Promocion> listaPromociones;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        sendEmail = new SendEmail();
        promocion = new Promocion();
        listaPromociones = promocionEJB.findAll();
        
        promocion.setIdEstablecimiento(1L);
        promocion.setImagen("no-image");
        promocion.setEstado("Activo");
        promocion.setFechaRegistro(new Date());
    }
    
    public void guardarPromocion(){
        log.info("Se va a registrar una nueva promocion.");
        
        try {
            //promocionEJB.create(promocion);
            for(Usuario usr:usuarioEJB.findAll()){
                if(usr.getIdPerfil() == 3){
                    EmailMessageDTO emailMessageDto = new EmailMessageDTO();
                    emailMessageDto.setMassive(true);
                    emailMessageDto.setTo(usr.getCorreo());
                    emailMessageDto.setSubject("Ensayapp: Aprovecha nuestra promo");
                    String body = "<h1>"+promocion.getTitulo()+"</h1>";
                    body += "<hr/>";
                    body += "<p>"+promocion.getDescripcion()+"</p>";

                    emailMessageDto.setBody(body);

                    String emailResponse;
                    emailResponse = sendEmail.sendEmailMessage(emailMessageDto);

                    log.info("Email response: "+emailResponse);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "La promoción ha sido registrado en el sistema.\n"));
            
        } catch (Exception e) {
            log.info("EXCEPTION::: \n"+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "No fue posible registrar la promoción.\n"+e.getMessage()));
        }
    }
    
    public void eliminarPromocion(){
        try {
            promocion.setEstado("Inactivo");
            promocionEJB.edit(promocion);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void activarPromocion(){
        try {
            promocion.setEstado("Activo");
            promocionEJB.edit(promocion);
        } catch (Exception e) {
            throw e;
        }
    }
    
    // Getters & setters

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public List<Promocion> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPromociones(List<Promocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }
    
    
}
