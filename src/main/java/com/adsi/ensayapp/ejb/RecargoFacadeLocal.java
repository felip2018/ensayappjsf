/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Recargo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface RecargoFacadeLocal {

    void create(Recargo recargo);

    void edit(Recargo recargo);

    void remove(Recargo recargo);

    Recargo find(Object id);

    List<Recargo> findAll();

    List<Recargo> findRange(int[] range);

    int count();
    
}
