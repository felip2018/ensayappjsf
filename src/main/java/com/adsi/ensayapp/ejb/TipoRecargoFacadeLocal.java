/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.TipoRecargo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface TipoRecargoFacadeLocal {

    void create(TipoRecargo tipoRecargo);

    void edit(TipoRecargo tipoRecargo);

    void remove(TipoRecargo tipoRecargo);

    TipoRecargo find(Object id);

    List<TipoRecargo> findAll();

    List<TipoRecargo> findRange(int[] range);

    int count();
    
}
