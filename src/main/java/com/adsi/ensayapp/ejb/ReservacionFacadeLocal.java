/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface ReservacionFacadeLocal {

    void create(Reservacion reservacion);

    void edit(Reservacion reservacion);

    void remove(Reservacion reservacion);

    Reservacion find(Object id);

    List<Reservacion> findAll();

    List<Reservacion> findRange(int[] range);

    int count();
    
    List<Reservacion> findAllByUser(Usuario usuario);
    
    void updateStatus(Reservacion reservacion,Long idStatus);
    
    List<Reservacion> findAllByUserAndState(Usuario usuario, Long idState);
    
    Long countActiveReservations(int idUsuario);
}
