package com.adsi.ensayapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("doctypeConverter")
public class DoctypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String doctype = "";
        if (value != null) {
            int idDoctype = (int) value;

            switch (idDoctype) {
                case 1:
                    doctype = "CC";
                    break;
                case 2:
                    doctype = "CE";
                    break;
                case 3:
                    doctype = "PAS";
                    break;
                case 4:
                    doctype = "TI";
                    break;
                case 5:
                    doctype = "NIT";
                    break;
            }

        }
        return doctype;
    }
}
