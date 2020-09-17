/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Felipe
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

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
