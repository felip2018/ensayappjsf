package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.EstadoReservaFacadeLocal;
import com.adsi.ensayapp.model.EstadoReserva;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("stateConverter")
public class StateConverter implements Converter {
    
    @EJB
    private EstadoReservaFacadeLocal estadoEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        EstadoReserva estado = new EstadoReserva();
        if (value != null) {
            int idEstado = (int) value;
            estado = estadoEJB.find(idEstado);
        }else{
            estado.setEstado("UNDEFINED");
        }
        
        return estado.getEstado();
    }
}
