package com.adsi.ensayapp.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.adsi.ensayapp.ejb.SucursalFacadeLocal;
import com.adsi.ensayapp.model.Sucursal;
import java.util.List;
import javax.annotation.PostConstruct;

@Named
@ViewScoped
public class BranchOfficeBean implements Serializable{
    
    @EJB
    private SucursalFacadeLocal sucursalEJB;
    
    private List<Sucursal> listadoSucursales;
    
    @PostConstruct
    public void init(){
        listadoSucursales = sucursalEJB.findAll();
    }
    
    
    
    // Getters and setters

    public List<Sucursal> getListadoSucursales() {
        return listadoSucursales;
    }

    public void setListadoSucursales(List<Sucursal> listadoSucursales) {
        this.listadoSucursales = listadoSucursales;
    }
    
}
