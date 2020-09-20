package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Sala;
import java.io.Serializable;
import java.util.Date;
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
public class RegisterRoomForm implements Serializable {
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    private Sala sala;
    
    @PostConstruct
    public void init(){
        sala = new Sala();
        sala.setIdSucursal(1);
        sala.setFechaRegistro(new Date());
        sala.setEstadoRegistro("Activo");
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public void registrarSala(){
        try {
            salaEJB.create(sala);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "La sala de ensayo ha sido registrada!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "No ha sido posible registrar la sala de ensayo."));
        }
    }
}
