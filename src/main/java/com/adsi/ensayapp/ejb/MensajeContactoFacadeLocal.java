/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.MensajeContacto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface MensajeContactoFacadeLocal {

    void create(MensajeContacto mensajeContacto);

    void edit(MensajeContacto mensajeContacto);

    void remove(MensajeContacto mensajeContacto);

    MensajeContacto find(Object id);

    List<MensajeContacto> findAll();

    List<MensajeContacto> findRange(int[] range);

    int count();
    
}
