package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Sala;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    @PostConstruct
    public void init(){
        salas = buscarListaSalas();
        reservacion = new Reservacion();
        reservacion.setIdUsuario(8);
        reservacion.setIdEstadoReserva(1);
        reservacion.setCalificacion(0);
        reservacion.setFechaRegistro(new Date());
        reservacion.setEstadoRegistro("Activo");
    }

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
        } catch (Exception e) {
            throw e;
        }
    }
}
