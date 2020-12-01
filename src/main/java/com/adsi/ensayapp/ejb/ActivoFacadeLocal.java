/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Activo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface ActivoFacadeLocal {

    void create(Activo activo);

    void edit(Activo activo);

    void remove(Activo activo);

    Activo find(Object id);

    List<Activo> findAll();

    List<Activo> findRange(int[] range);

    int count();
    
    List<Object[]> getAssetsByCurrentState();
}
