package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.TipoGastoFacadeLocal;
import com.adsi.ensayapp.model.TipoGasto;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("spendingConverter")
public class SpendingConverter implements Converter {

    @EJB
    private TipoGastoFacadeLocal tipoGastoEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TipoGasto tipoGasto = new TipoGasto();
        if (value != null) {
            int idTipoGasto = (int) value;
            tipoGasto = tipoGastoEJB.find(idTipoGasto);
        }else{
            tipoGasto.setNombre("UNDEFINED");
        }
        
        return tipoGasto.getNombre();
    }

}
