package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.ActivoFacadeLocal;
import com.adsi.ensayapp.model.Activo;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("instrumentConverter")
public class InstrumentConverter implements Converter {
    
    @EJB
    private ActivoFacadeLocal activoEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Activo activo = new Activo();
        if (value != null) {
            int idActivo = (int) value;
            activo = activoEJB.find(idActivo);
        }else{
            activo.setNombre("UNDEFINED");
        }
        
        return activo.getNombre();
    }
    
}
