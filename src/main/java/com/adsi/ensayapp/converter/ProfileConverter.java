package com.adsi.ensayapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("profileConverter")
public class ProfileConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String profile = "";
        if (value != null) {
            int idProfile = (int) value;

            switch (idProfile) {
                case 1:
                    profile = "ADMINISTRADOR";
                    break;
                case 2:
                    profile = "AUXILIAR";
                    break;
                case 3:
                    profile = "MÚSICO";
                    break;
            }

        }
        return profile;
    }
}
