/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Usuario;
import java.util.ArrayList;
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
public class ReservacionFacade extends AbstractFacade<Reservacion> implements ReservacionFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservacionFacade() {
        super(Reservacion.class);
    }

    @Override
    public List<Reservacion> findAllByUser(Usuario usr) {
        List<Reservacion> lista = new ArrayList<>();
        String consulta;
        
        try {
            
            consulta = "FROM Reservacion r WHERE r.idUsuario = ?1";
            
            Query query = em.createQuery(consulta);
            query.setParameter(1, usr.getId());
            
            lista = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        
        return lista;
    }
    
}
