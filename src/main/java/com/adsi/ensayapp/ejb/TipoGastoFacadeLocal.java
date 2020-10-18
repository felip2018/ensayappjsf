/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.TipoGasto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface TipoGastoFacadeLocal {

    void create(TipoGasto tipoGasto);

    void edit(TipoGasto tipoGasto);

    void remove(TipoGasto tipoGasto);

    TipoGasto find(Object id);

    List<TipoGasto> findAll();

    List<TipoGasto> findRange(int[] range);

    int count();
    
}
