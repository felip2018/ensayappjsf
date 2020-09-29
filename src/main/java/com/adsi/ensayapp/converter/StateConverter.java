package com.adsi.ensayapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("stateConverter")
public class StateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String state = "";
        if (value != null) {
            int idState = (int) value;

            switch (idState) {
                case 1:
                    state = "ABIERTA";
                    break;
                case 2:
                    state = "CONFIRMADA";
                    break;
                case 3:
                    state = "CANCELADA";
                    break;
                case 4:
                    state = "RECARGO";
                    break;
                case 5:
                    state = "FINALIZADA";
                    break;
            }

        }
        return state;
    }
}
