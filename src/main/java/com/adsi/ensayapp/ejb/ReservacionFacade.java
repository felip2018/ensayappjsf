/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Felipe
 */
@Stateless
public class ReservacionFacade extends AbstractFacade<Reservacion> implements ReservacionFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservacionFacade() {
        super(Reservacion.class);
    }

    @Override
    public List<Reservacion> findAllByUser(Usuario usr) {
        List<Reservacion> lista = new ArrayList<>();
        String consulta;

        try {

            consulta = "FROM Reservacion r WHERE r.idUsuario = ?1";

            Query query = em.createQuery(consulta);
            query.setParameter(1, usr.getId());

            lista = query.getResultList();

        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    @Override
    public void updateStatus(Reservacion reservacion, Long idStatus) {
        String consulta;
        try {
            consulta = "UPDATE Reservacion r SET r.idEstadoReserva = ?1 WHERE r.idReservacion = ?2";

            Query query = em.createQuery(consulta);
            query.setParameter(1, idStatus);
            query.setParameter(2, reservacion.getIdReservacion());

            query.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Reservacion> findAllByUserAndState(Usuario usr, Long idState) {
        List<Reservacion> lista = new ArrayList<>();
        String consulta;

        try {

            consulta = "FROM Reservacion r WHERE r.idUsuario = ?1 AND r.idEstadoReserva = ?2";

            Query query = em.createQuery(consulta);
            query.setParameter(1, usr.getId());
            query.setParameter(2, idState);

            lista = query.getResultList();

        } catch (Exception e) {
            throw e;
        }

        return lista;
    }

    @Override
    public Long countActiveReservations(int idUsuario) {
        Long response = null;
        String consulta;
        try {
            consulta = "SELECT COUNT(r) FROM Reservacion r WHERE r.idUsuario = ?1 AND r.idEstadoReserva = 1";

            Query query = em.createQuery(consulta);
            query.setParameter(1, idUsuario);

            response = (Long) query.getSingleResult();

        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public List<Object[]> getReservationsByState() {
        List<Object[]> response = null;
        String consulta;
        try {

            consulta = "SELECT er.estado, count(*) \n"
                    + "FROM reservacion r\n"
                    + "INNER JOIN estado_reserva er ON er.id_estado_reserva = r.id_estado_reserva \n"
                    + "GROUP BY r.id_estado_reserva;";

            Query query = em.createNativeQuery(consulta);
            response = query.getResultList();

        } catch (Exception e) {
            response = null;
        }

        return response;
    }

    @Override
    public List<Object[]> getReservationsNumberByRoom() {
        List<Object[]> response = null;
        String consulta;
        try {

            consulta = "SELECT s.nombre, COUNT(*) cant\n"
                    + "FROM reservacion r\n"
                    + "INNER JOIN sala s ON s.id_sala = r.id_sala\n"
                    + "GROUP BY r.id_sala\n"
                    + "ORDER BY cant DESC \n"
                    + "LIMIT 3;";

            Query query = em.createNativeQuery(consulta);
            response = query.getResultList();

        } catch (Exception e) {
            response = null;
        }

        return response;
    }

}
