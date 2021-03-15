/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.SistemaHora;
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
public class SistemaHoraFacade extends AbstractFacade<SistemaHora> implements SistemaHoraFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemaHoraFacade() {
        super(SistemaHora.class);
    }

    @Override
    public List<SistemaHora> findAllByState() {
        List<SistemaHora> response = null;
        String consulta;
        try {
            consulta = "FROM SistemaHora sh WHERE sh.estadoRegistro = 'Activo' ORDER BY sh.idSistemaHora ASC";
            
            Query query = em.createQuery(consulta);
            
            response = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        
        return response;
    }
}
