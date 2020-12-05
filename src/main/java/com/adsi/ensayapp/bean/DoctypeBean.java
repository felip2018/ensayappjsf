package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.TipoDocumentoFacadeLocal;
import com.adsi.ensayapp.model.TipoDocumento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class DoctypeBean implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private TipoDocumentoFacadeLocal tipodocEJB;
    
    private List<TipoDocumento> listadoTipoDocumentos;
    
    @PostConstruct
    public void init(){
        listadoTipoDocumentos = tipodocEJB.findAll();
    }
    
    
    
    // Getters and setters
    public List<TipoDocumento> getListadoTipoDocumentos() {
        return listadoTipoDocumentos;
    }

    public void setListadoTipoDocumentos(List<TipoDocumento> listadoTipoDocumentos) {
        this.listadoTipoDocumentos = listadoTipoDocumentos;
    }
    
}
