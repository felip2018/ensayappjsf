package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Sala;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("roomConverter")
public class RoomConverter implements Converter {
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Sala sala = new Sala();
        if (value != null) {
            int idSala = (int) value;
            sala = salaEJB.find(idSala);
        }else{
            sala.setNombre("UNDEFINED");
        }
        
        return sala.getNombre();
    }
}
