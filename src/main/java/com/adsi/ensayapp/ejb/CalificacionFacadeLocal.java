/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Calificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface CalificacionFacadeLocal {

    void create(Calificacion calificacion);

    void edit(Calificacion calificacion);

    void remove(Calificacion calificacion);

    Calificacion find(Object id);

    List<Calificacion> findAll();

    List<Calificacion> findRange(int[] range);

    int count();
    
}