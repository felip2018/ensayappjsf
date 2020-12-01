/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Activo;
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
public class ActivoFacade extends AbstractFacade<Activo> implements ActivoFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActivoFacade() {
        super(Activo.class);
    }

    @Override
    public List<Object[]> getAssetsByCurrentState() {
        List<Object[]> response = null;
        String consulta;
        try {
            consulta = "SELECT \n"
                    + "estado_activo,\n"
                    + "COUNT(*)\n"
                    + "FROM activo\n"
                    + "GROUP BY estado_activo";

            Query query = em.createNativeQuery(consulta);
            response = query.getResultList();

        } catch (Exception e) {
            response = null;
        }
        return response;
    }

}
