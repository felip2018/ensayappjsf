package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.backing.TextExporter;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

@Named
@ViewScoped
public class Reservations implements Serializable {
    
    private List<Reservacion> listaReservaciones;
    private Exporter<DataTable> textExporter;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
            
    @PostConstruct
    public void init(){
        listaReservaciones = reservacionEJB.findAll();
        textExporter = new TextExporter();
    }

    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public void setListaReservaciones(List<Reservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }
    
    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }
 
    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }
    
}
