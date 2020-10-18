package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.PerfilFacadeLocal;
import com.adsi.ensayapp.model.Perfil;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("profileConverter")
public class ProfileConverter implements Converter {
    
    @EJB
    private PerfilFacadeLocal perfilEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Perfil perfil = new Perfil();
        if (value != null) {
            int idPerfil = (int) value;
            perfil = perfilEJB.find(idPerfil);
        }else{
            perfil.setNombre("UNDEFINED");
        }
        
        return perfil.getNombre();
    }
}
