/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.EstadoReserva;
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
public class EstadoReservaFacade extends AbstractFacade<EstadoReserva> implements EstadoReservaFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoReservaFacade() {
        super(EstadoReserva.class);
    }

    @Override
    public List<EstadoReserva> findAllToCalendar() {
        List<EstadoReserva> response = null;
        String consulta;
        try {
            consulta = "FROM EstadoReserva er WHERE er.estadoRegistro = 'Activo' AND er.idEstadoReserva IN (2,3,5)";
            
            Query query = em.createQuery(consulta);
            
            response = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        
        return response;
    }
    
}
