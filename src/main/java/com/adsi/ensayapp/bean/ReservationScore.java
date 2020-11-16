package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.CalificacionFacadeLocal;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.model.Calificacion;
import com.adsi.ensayapp.model.Reservacion;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class ReservationScore implements Serializable {
    
    Logger log = LogManager.getRootLogger();
        
    private Reservacion reservacion;
    private int sonido;
    private int instalaciones;
    private int puntualidad;
    private int equipos;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    @EJB
    private CalificacionFacadeLocal calificacionEJB;
    
    public void guardarCalificacion(){
        try {
            
            int[] lineamientos;
            lineamientos = new int[]{sonido,instalaciones,puntualidad,equipos};
                        
            for (int i = 0; i < lineamientos.length; i++) {
                Calificacion calificacion = new Calificacion();
                
                calificacion.setIdReservacion(reservacion.getIdReservacion());
                calificacion.setIdLineamientoCalificacion(i+1);
                calificacion.setPuntaje(lineamientos[i]);
                calificacion.setComentarios("");
                calificacion.setFechaRegistro(new Date());
                calificacion.setEstadoRegistro("Activo");
                                
                calificacionEJB.create(calificacion);           
            }
            
            reservacionEJB.updateStatus(reservacion, 6L);            
                        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "Error en el sistema: "+e.getMessage()));
        }
    }    
    
    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(int instalaciones) {
        this.instalaciones = instalaciones;
    }

    public int getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(int puntualidad) {
        this.puntualidad = puntualidad;
    }

    public int getEquipos() {
        return equipos;
    }

    public void setEquipos(int equipos) {
        this.equipos = equipos;
    }
        
        
    
}
