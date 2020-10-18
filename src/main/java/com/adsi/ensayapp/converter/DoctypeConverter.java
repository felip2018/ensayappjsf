package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.TipoDocumentoFacadeLocal;
import com.adsi.ensayapp.model.TipoDocumento;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("doctypeConverter")
public class DoctypeConverter implements Converter {
    
    @EJB
    private TipoDocumentoFacadeLocal tipoDocumentoEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        if (value != null) {
            int idTipoDocumento = (int) value;
            tipoDocumento = tipoDocumentoEJB.find(idTipoDocumento);
        }else{
            tipoDocumento.setNombre("UNDEFINED");
        }
        
        return tipoDocumento.getNombre();
    }
}
