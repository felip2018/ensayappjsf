package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class AccountValidation implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    private String validationCode;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    public String validateUserAccount(){
        String urlRedireccion = null;
        try{
            usuarioEJB.validarCuenta(validationCode);
            urlRedireccion = "login?faces-redirect=true";
        }catch(Exception e){
            throw e;
        }
        return urlRedireccion;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
    
    
}
