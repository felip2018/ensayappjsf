/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.LineamientoCalificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface LineamientoCalificacionFacadeLocal {

    void create(LineamientoCalificacion lineamientoCalificacion);

    void edit(LineamientoCalificacion lineamientoCalificacion);

    void remove(LineamientoCalificacion lineamientoCalificacion);

    LineamientoCalificacion find(Object id);

    List<LineamientoCalificacion> findAll();

    List<LineamientoCalificacion> findRange(int[] range);

    int count();
    
}
