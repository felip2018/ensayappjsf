/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.dto.UserValidationResponseDTO;
import com.adsi.ensayapp.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    UserValidationResponseDTO validacionUsuario(Usuario usr);
    
    Usuario iniciarSesion(Usuario usr);
    
    void validarCuenta(String codigoValidacion);
}
