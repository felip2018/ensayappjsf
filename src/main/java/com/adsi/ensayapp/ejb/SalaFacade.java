/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Sala;
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
public class SalaFacade extends AbstractFacade<Sala> implements SalaFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalaFacade() {
        super(Sala.class);
    }

    @Override
    public List<Sala> findAllByBranchOffice(Long idSucursal) {
        List<Sala> response = null;
        String consulta;
        try {
            consulta = "FROM Sala s WHERE s.idSucursal = ?1";
            
            Query query = em.createQuery(consulta);
            query.setParameter(1, idSucursal);
            
            response = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
        
        return response;
    }
    
}
