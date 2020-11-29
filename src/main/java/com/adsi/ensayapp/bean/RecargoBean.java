package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.RecargoFacadeLocal;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.ejb.TipoRecargoFacadeLocal;
import com.adsi.ensayapp.model.Recargo;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.TipoRecargo;
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
public class RecargoBean implements Serializable {
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private RecargoFacadeLocal recargoEJB;
    
    @EJB
    private TipoRecargoFacadeLocal tipoRecargoEJB;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    private List<Recargo> listadoRecargos;
    
    private Recargo recargo;
    
    private Reservacion reservacion;
    
    private List<TipoRecargo> listaTiposRecargo;
    
    @PostConstruct
    public void init(){
        recargo = new Recargo();
        
        recargo.setFechaRegistro(new Date());
        recargo.setEstadoRegistro("Activo");
        recargo.setFechaPago(null);
        recargo.setComentarios("");
        
        listaTiposRecargo = tipoRecargoEJB.findAll();
    }
    
    // Registrar recargo
    public void registrarRecargo(){
        try {            
            recargo.setIdReservacion(reservacion.getIdReservacion());
            log.info("Id Reservacion: "+recargo.getIdReservacion());
            log.info("Monto: "+recargo.getMonto());
            log.info("Descripcion: "+recargo.getDescripcionCaso());
            log.info("Tipo Recargo: "+recargo.getIdTipoRecargo());
            recargoEJB.create(recargo);
            
            reservacion.setIdEstadoReserva(4);
            reservacionEJB.edit(reservacion);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "Se ha realizado la reservación de la sala de ensayo."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "Ha ocurrido un error: "+e.getMessage()));
        }
    }
    
    
    // Getters and setters

    public List<Recargo> getListadoRecargos() {
        return listadoRecargos;
    }

    public void setListadoRecargos(List<Recargo> listadoRecargos) {
        this.listadoRecargos = listadoRecargos;
    }

    public Recargo getRecargo() {
        return recargo;
    }

    public void setRecargo(Recargo recargo) {
        this.recargo = recargo;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public List<TipoRecargo> getListaTiposRecargo() {
        return listaTiposRecargo;
    }

    public void setListaTiposRecargo(List<TipoRecargo> listaTiposRecargo) {
        this.listaTiposRecargo = listaTiposRecargo;
    }
    
}
