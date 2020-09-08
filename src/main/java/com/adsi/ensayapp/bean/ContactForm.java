package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.MensajeContactoFacadeLocal;
import com.adsi.ensayapp.model.MensajeContacto;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named 
@ViewScoped
public class ContactForm implements Serializable {
    
    @EJB
    private MensajeContactoFacadeLocal mensajeContactoEJB;
    
    private MensajeContacto mensajeContacto;
    
    @PostConstruct
    public void init(){
        mensajeContacto = new MensajeContacto();
    }
    
    public MensajeContacto getMensajeContacto() {
        return mensajeContacto;
    }
    
    public void setMensajeContacto(MensajeContacto mensajeContacto) {
        this.mensajeContacto = mensajeContacto;
    }
    
    public void enviar() {
        try {
            mensajeContactoEJB.create(mensajeContacto);
        } catch (Exception e) {
            throw e;
        }
    }
   
}
