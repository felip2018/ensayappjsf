package com.adsi.ensayapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("timeConverter")
public class TimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String time = "";
        if (value != null) {
            int idTime = (int) value;

            switch (idTime) {
                case 1:
                    time = "08:00 A.M - 10:00 A.M";
                    break;
                case 2:
                    time = "10:00 A.M - 12:00 P.M";
                    break;
                case 3:
                    time = "12:00 P.M - 02:00 P.M";
                    break;
                case 4:
                    time = "02:00 P.M - 04:00 P.M";
                    break;
                case 5:
                    time = "04:00 P.M - 06:00 P.M";
                    break;
            }

        }
        return time;
    }
}
