package com.adsi.ensayapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("roomConverter")
public class RoomConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String room = "";
        if (value != null) {
            int idRoom = (int) value;

            switch (idRoom) {
                case 1:
                    room = "SALA AMARILLA";
                    break;
                case 2:
                    room = "SALA AZUL";
                    break;
                case 3:
                    room = "SALA VERDE";
                    break;
                case 4:
                    room = "SALA MORADA";
                    break;
            }

        }
        return room;
    }
}
