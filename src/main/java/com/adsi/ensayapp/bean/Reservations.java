package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.backing.TextExporter;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

@Named
@ViewScoped
public class Reservations implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    private List<Reservacion> listaReservaciones;
    private Exporter<DataTable> textExporter;
    private List<Reservacion> listaReservacionesPorUsuario;
    private List<Reservacion> listaReservacionesUsuarioEstadoReserva;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
            
    @PostConstruct
    public void init(){
        this.getReservationsByUser();
        this.getReservationsByUserAndState();
        listaReservaciones = reservacionEJB.findAll();
        textExporter = new TextExporter();
    }
    
    public void getReservationsByUser(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            listaReservacionesPorUsuario = reservacionEJB.findAllByUser(u);
            log.info("Numero de reservaciones: "+listaReservacionesPorUsuario.size());
        } catch (Exception e) {
            log.info("Error al consultar reservaciones por usuario: "+e.getMessage());
        }
    }
    
    public void getReservationsByUserAndState(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            listaReservacionesUsuarioEstadoReserva = reservacionEJB.findAllByUserAndState(u, 1L);
            
        } catch (Exception e) {
            log.info("Error al consultar reservaciones: "+e.getMessage());
        }
    }
    
    public List<Reservacion> getListaReservacionesPorUsuario() {
        return listaReservacionesPorUsuario;
    }

    public void setListaReservacionesPorUsuario(List<Reservacion> listaReservacionesPorUsuario) {
        this.listaReservacionesPorUsuario = listaReservacionesPorUsuario;
    }
        
    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public void setListaReservaciones(List<Reservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }
    
    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }
 
    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }

    public List<Reservacion> getListaReservacionesUsuarioEstadoReserva() {
        return listaReservacionesUsuarioEstadoReserva;
    }

    public void setListaReservacionesUsuarioEstadoReserva(List<Reservacion> listaReservacionesUsuarioEstadoReserva) {
        this.listaReservacionesUsuarioEstadoReserva = listaReservacionesUsuarioEstadoReserva;
    }
    
}
