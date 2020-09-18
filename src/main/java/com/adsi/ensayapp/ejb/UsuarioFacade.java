/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.dto.UserValidationResponseDTO;
import com.adsi.ensayapp.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felipe
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    
    Logger log = LogManager.getRootLogger();
    
    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public UserValidationResponseDTO validacionUsuario(Usuario usr){
        Usuario usuario = null;
        UserValidationResponseDTO validacion = new UserValidationResponseDTO();
        String consulta;
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_UserValidation",UserValidationResponseDTO.class);
            storedProcedure.registerStoredProcedureParameter("idTipoDoc", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("numDoc", Double.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("correoElectronico", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("cant", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
            
            storedProcedure.setParameter("idTipoDoc", usr.getIdTipoDocumento());
            storedProcedure.setParameter("numDoc", usr.getNumDoc());
            storedProcedure.setParameter("correoElectronico", usr.getCorreo());
            
            storedProcedure.execute();
            
            validacion.setCant((int) storedProcedure.getOutputParameterValue("cant"));
            validacion.setMensaje((String) storedProcedure.getOutputParameterValue("mensaje"));
            
            
        } catch (Exception e) {
            throw e;
        } 
        
        return validacion;
    }
    
    @Override
    public Usuario iniciarSesion(Usuario usr){
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.correo = ?1 AND u.clave = ?2";
            
            Query query = em.createQuery(consulta);
            query.setParameter(1, usr.getCorreo());
            query.setParameter(2, usr.getClave());
            
            List<Usuario> lista = query.getResultList();
            
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
            
        } catch (Exception e) {
            throw e;
        } 
        
        return usuario;
    }
}
