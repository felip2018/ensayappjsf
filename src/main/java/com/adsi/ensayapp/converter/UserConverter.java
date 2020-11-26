package com.adsi.ensayapp.converter;

import com.adsi.ensayapp.ejb.EstadoReservaFacadeLocal;
import com.adsi.ensayapp.ejb.UsuarioFacadeLocal;
import com.adsi.ensayapp.model.EstadoReserva;
import com.adsi.ensayapp.model.Usuario;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("userConverter")
public class UserConverter implements Converter {
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Usuario usuario = new Usuario();
        if (value != null) {
            int idUsuario = (int) value;
            usuario = usuarioEJB.find(idUsuario);
        }else{
            usuario.setNombre("UNDEFINED");
            usuario.setApellido("UNDEFINED");
            usuario.setNumDoc(0);
        }
        
        return usuario.getNombre()+" "+usuario.getApellido()+" / "+usuario.getNumDoc();
    }
}
