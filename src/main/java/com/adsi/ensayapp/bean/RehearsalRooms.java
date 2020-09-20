package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Sala;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RehearsalRooms implements Serializable {
    
    private List<Sala> salas;
    private Sala sala;
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @PostConstruct
    public void init(){
        salas = buscarListaSalas();
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
    
    public List<Sala> buscarListaSalas(){
        List<Sala> listado = null;
        try {
            listado = salaEJB.findAll();
        } catch (Exception e) {
            throw e;
        }
        return listado;
    }
}
