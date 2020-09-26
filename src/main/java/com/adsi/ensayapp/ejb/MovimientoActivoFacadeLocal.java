/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.MovimientoActivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface MovimientoActivoFacadeLocal {

    void create(MovimientoActivo movimientoActivo);

    void edit(MovimientoActivo movimientoActivo);

    void remove(MovimientoActivo movimientoActivo);

    MovimientoActivo find(Object id);

    List<MovimientoActivo> findAll();

    List<MovimientoActivo> findRange(int[] range);

    int count();
    
}
