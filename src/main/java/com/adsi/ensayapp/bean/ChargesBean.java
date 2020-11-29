package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.ChargeInfoDTO;
import com.adsi.ensayapp.ejb.RecargoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@ViewScoped
public class ChargesBean implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private RecargoFacadeLocal recargoEJB;
    private List<Object[]> listadoRegistros;
    private List<ChargeInfoDTO> listaRecargos;
    
    private ChargeInfoDTO cargo;
    
    @PostConstruct
    public void init(){
        listaRecargos = new ArrayList<>();
        listadoRegistros = recargoEJB.getAllCharges();
        for (Object[] listadoRegistro : listadoRegistros) {
            ChargeInfoDTO charge = new ChargeInfoDTO();
            charge.setMonto((double) listadoRegistro[0]);
            charge.setFechaRegistro((String) listadoRegistro[1]);
            charge.setFechaReservacion((String) listadoRegistro[2]);
            charge.setSala((String) listadoRegistro[3]);
            charge.setTipoRecargo((String) listadoRegistro[4]);
            charge.setUsuario((String) listadoRegistro[5]);
            charge.setEstadoRecargo((String) listadoRegistro[6]);
            charge.setIdentificacion((String) listadoRegistro[7]);
            charge.setIdRecargo((int) listadoRegistro[8]);
            
            listaRecargos.add(charge);
        }
    }
    
    public void selectCharge(){
        log.info("Seleccion de recargo");
    }
    
    
    // Getters and setters

    public List<ChargeInfoDTO> getListaRecargos() {
        return listaRecargos;
    }

    public void setListaRecargos(List<ChargeInfoDTO> listaRecargos) {
        this.listaRecargos = listaRecargos;
    }

    public ChargeInfoDTO getCargo() {
        return cargo;
    }

    public void setCargo(ChargeInfoDTO cargo) {
        this.cargo = cargo;
    }
    
}
