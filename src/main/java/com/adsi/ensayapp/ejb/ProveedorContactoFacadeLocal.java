/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.ProveedorContacto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface ProveedorContactoFacadeLocal {

    void create(ProveedorContacto proveedorContacto);

    void edit(ProveedorContacto proveedorContacto);

    void remove(ProveedorContacto proveedorContacto);

    ProveedorContacto find(Object id);

    List<ProveedorContacto> findAll();

    List<ProveedorContacto> findRange(int[] range);

    int count();
    
}
