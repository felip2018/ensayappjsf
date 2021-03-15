/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.SistemaHora;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface SistemaHoraFacadeLocal {

    void create(SistemaHora sistemaHora);

    void edit(SistemaHora sistemaHora);

    void remove(SistemaHora sistemaHora);

    SistemaHora find(Object id);

    List<SistemaHora> findAll();

    List<SistemaHora> findRange(int[] range);

    int count();
    
    List<SistemaHora> findAllByState();
}
