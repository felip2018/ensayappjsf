package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SistemaHoraFacadeLocal;
import com.adsi.ensayapp.model.SistemaHora;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HourSystem implements Serializable {
    
    @EJB
    private SistemaHoraFacadeLocal sistemaHoraEJB;
    
    private List<SistemaHora> listaHoras;
    
    @PostConstruct
    public void init(){
        listaHoras = sistemaHoraEJB.findAll();
    }
    
    
    // Getters and setters

    public List<SistemaHora> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<SistemaHora> listaHoras) {
        this.listaHoras = listaHoras;
    }
    
}
