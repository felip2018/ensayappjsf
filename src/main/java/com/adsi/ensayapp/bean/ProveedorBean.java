package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ProveedorFacadeLocal;
import com.adsi.ensayapp.model.Proveedor;
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
public class ProveedorBean implements Serializable {

    Logger log = LogManager.getRootLogger();

    @EJB
    private ProveedorFacadeLocal proveedorEJB;

    private Proveedor proveedor;

    private List<Proveedor> listaProveedores;

    @PostConstruct
    public void init() {
        listaProveedores = proveedorEJB.findAll();
        proveedor = new Proveedor();

        proveedor.setEstadoRegistro("Activo");
        proveedor.setFechaRegistro(new Date());

    }
    
    public void guardarProveedor(){
        log.info("Le has dado clic al boton de guardar proveedor");
        log.info("Vas a guardar el proveedor: "+proveedor.getRazonSocial());
        
        try {
            
            proveedorEJB.create(proveedor);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "El proveedor ha sido registrado en el sistema."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "No fue posible registrar el proveedor.\n"+e.getMessage()));
        }
        
    }
    
    
    
    
    
    // Getters and setters
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

}
