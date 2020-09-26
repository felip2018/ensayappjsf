/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Gasto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface GastoFacadeLocal {

    void create(Gasto gasto);

    void edit(Gasto gasto);

    void remove(Gasto gasto);

    Gasto find(Object id);

    List<Gasto> findAll();

    List<Gasto> findRange(int[] range);

    int count();
    
}
