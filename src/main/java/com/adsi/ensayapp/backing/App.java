package com.adsi.ensayapp.backing;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class App implements Serializable {
    
    private static final long serialVersionUID = 84157941310458440L;
    
    private String locale;
    
    public App(){
        locale = "ES";
    }

    public String getLocale() {
        return locale;
    }
    
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    // Cambiar idioma
    public void cambiarIdioma(String idioma){
        if (idioma.equals("EN")) {
            //FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
            locale = idioma;
        }else{
            //FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.getDefault());
            locale = "ES";
        }
    }
}
