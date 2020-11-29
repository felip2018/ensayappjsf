package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Recargo;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RecargoFacade extends AbstractFacade<Recargo> implements RecargoFacadeLocal {

    @PersistenceContext(unitName = "ensayapp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecargoFacade() {
        super(Recargo.class);
    }

    @Override
    public List<Object[]> getAllCharges() {
        List<Object[]> response = null;
        String consulta = "SELECT \n"
                + "r.monto,\n"
                + "DATE_FORMAT(r.fecha_registro, \"%Y-%m-%d\") as fecha_registro,\n"
                + "DATE_FORMAT(re.fecha, \"%Y-%m-%d\") as fecha_reservacion,\n"
                + "s.nombre as sala,\n"
                + "tr.nombre as tipo_recargo,\n"
                + "concat(u.nombre,' ',u.apellido) as usuario,\n"
                + "r.estado_registro as estado_recargo,\n"
                + "concat(td.abreviacion,' ',u.num_doc) as identificacion,\n"
                + "r.id_recargo\n"
                + "FROM recargo r\n"
                + "INNER JOIN reservacion re ON re.id_reservacion = r.id_reservacion\n"
                + "INNER JOIN sala s ON s.id_sala = re.id_sala\n"
                + "INNER JOIN usuario u ON u.id_usuario = re.id_usuario\n"
                + "INNER JOIN tipo_doc td ON td.id_tipo_doc = u.id_tipo_doc\n"
                + "INNER JOIN tipo_recargo tr ON tr.id_tipo_recargo = r.id_tipo_recargo";

        try {
            Query query = em.createNativeQuery(consulta);
            response = query.getResultList();
        } catch (Exception e) {
            throw e;
        }

        return response;
    }

}
