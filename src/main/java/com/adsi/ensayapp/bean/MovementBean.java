package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ActivoFacadeLocal;
import com.adsi.ensayapp.model.MovimientoActivo;
import com.adsi.ensayapp.ejb.MovimientoActivoFacadeLocal;
import com.adsi.ensayapp.model.Activo;
import com.adsi.ensayapp.utilities.Util;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@Named
@ViewScoped
public class MovementBean implements Serializable{
    
    Logger log = LogManager.getRootLogger();
    private final Util util = new Util();
    
    @EJB
    private MovimientoActivoFacadeLocal movimientoEJB;
    
    @EJB
    private ActivoFacadeLocal activoEJB;
    
    private MovimientoActivo movimiento;
    
    private Activo activo;
    
    @PostConstruct
    public void init(){
        activo = new Activo();
        movimiento = new MovimientoActivo();
    }
    
    public void registrarEntrada(){
        log.info("Registro de entrada!");
        try {
            movimiento.setIdActivo(activo.getIdActivo());
            movimiento.setIdSala(0);
            movimiento.setTipoMovimiento("ENTRADA");
            movimiento.setMotivo("ENTRADA");
            movimiento.setFechaRegistro(new Date());
            movimiento.setEstadoRegistro("Activo");
            
            movimientoEJB.create(movimiento);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "Se ha realizado el ingreso del activo correctamente!"));
            
            activo.setEstadoActivo("EN BODEGA");
            activoEJB.edit(activo);
            
            movimiento = new MovimientoActivo();
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "No ha sido posible registrar el ingreso del activo."));
        }
    }
    
    public void registrarSalida(){
        log.info("Registro de salida!");
    }
    
    public void registrarMantenimiento(){
        log.info("Registro de mantenimiento!");
    }
    
    // Getters and setters

    public MovimientoActivo getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoActivo movimiento) {
        this.movimiento = movimiento;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }
    
    
}
