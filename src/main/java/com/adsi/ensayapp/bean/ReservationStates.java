package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.EstadoReservaFacadeLocal;
import com.adsi.ensayapp.model.EstadoReserva;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReservationStates implements Serializable {
    
    @EJB
    private EstadoReservaFacadeLocal estadoReservaEJB;
    
    private List<EstadoReserva> listaEstados;
            
    @PostConstruct
    public void init(){
        listaEstados = estadoReservaEJB.findAllToCalendar();
    }
    
    
    
    // Getters and Setters

    public List<EstadoReserva> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<EstadoReserva> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
}
