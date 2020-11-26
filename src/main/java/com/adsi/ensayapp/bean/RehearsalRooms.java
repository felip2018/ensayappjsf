package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Sala;
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
public class RehearsalRooms implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Logger log = LogManager.getRootLogger();
    
    private List<Sala> salas;
    private Sala sala;
    private Reservacion reservacion;
    private String paramId;
    private Usuario musico;
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        musico = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        salas = buscarListaSalas();
        reservacion = new Reservacion();
        reservacion.setIdUsuario(musico.getId());
        reservacion.setIdEstadoReserva(1);
        reservacion.setCalificacion(0);
        reservacion.setFechaRegistro(new Date());
        reservacion.setEstadoRegistro("Activo");
    }
    
    public void actualizarInformacionSala(){
        try {
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarSala(){
        try {
            sala.setEstadoRegistro("Inactivo");
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void activarSala(){
        try {
            sala.setEstadoRegistro("Activo");
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
    }
    
    // Getters and setters
    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }
    
    public List<Sala> buscarListaSalas(){
        List<Sala> listado = null;
        try {
            listado = salaEJB.findAll();
        } catch (Exception e) {
            throw e;
        }
        return listado;
    }
    
    public void detalleSala(){
        try {
            sala = salaEJB.find(Integer.parseInt(paramId));
        } catch (NumberFormatException e) {
            log.error("ERROR:"+e.getMessage());
        }
    }
    
    public void realizarReservacion(){
        try {
            reservacion.setIdSala(sala.getId());
            reservacion.setPrecio(sala.getPrecio());
            reservacionEJB.create(reservacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "Se ha realizado la reservación de la sala de ensayo."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "Ha ocurrido un error: "+e.getMessage()));
        }
    }
}
