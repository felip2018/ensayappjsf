package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.GastoFacadeLocal;
import com.adsi.ensayapp.ejb.TipoGastoFacadeLocal;
import com.adsi.ensayapp.model.Gasto;
import com.adsi.ensayapp.model.TipoGasto;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class Spendings implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    
    @EJB
    private GastoFacadeLocal gastoEJB;
    
    @EJB
    private TipoGastoFacadeLocal tipoGastoEJB;
    
    private Gasto gasto;
    
    private TipoGasto tipoGasto;
    
    private List<TipoGasto> listaTiposGasto;
    private List<Gasto> listaGastos;
    
    private UploadedFile file;
    
    @PostConstruct
    public void init(){
        listaTiposGasto = tipoGastoEJB.findAll();
        listaGastos = gastoEJB.findAll();
        
        gasto = new Gasto();
        gasto.setIdSucursal(1);
        gasto.setFechaRegistro(new Date());
        gasto.setEstadoRegistro("Activo");
        
    }
    
    // Methods
    public void registrarGasto(){
        try {
            gastoEJB.create(gasto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "El gasto ha sido registrado en el sistema."));            
        } catch (Exception e) {
            //throw e;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "No ha sido posible registrar el gasto en el sistema.\n"+e.getMessage()));
        }
    }
    
    public void actualizarGasto(){
        try {
            gastoEJB.edit(gasto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void activarInactivarGesto(String estado){
        try {
            gasto.setEstadoRegistro(estado);
            gastoEJB.edit(gasto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void cargarArchivo(){
        if (file != null) {
            try {
                InputStream input = file.getInputStream();

                XSSFWorkbook workbook = new XSSFWorkbook(input);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Gasto nuevoGasto = new Gasto();
                    Row currentRow = iterator.next();

                    if (i > 0) {

                        if (currentRow.getCell(0) != null
                                && currentRow.getCell(1) != null
                                && currentRow.getCell(2) != null) {

                            nuevoGasto.setIdSucursal(1);
                            nuevoGasto.setIdTipoGasto((int) currentRow.getCell(1).getNumericCellValue());
                            nuevoGasto.setMonto(currentRow.getCell(2).getNumericCellValue());
                            nuevoGasto.setFechaGasto(currentRow.getCell(3).getDateCellValue());
                            nuevoGasto.setComentatios(currentRow.getCell(4).getStringCellValue());
                            nuevoGasto.setFechaRegistro(new Date());
                            nuevoGasto.setEstadoRegistro("Activo");

                            gastoEJB.create(nuevoGasto);
                        } else {
                            log.info("No more data!");
                            break;
                        }
                    }
                    i++;
                }
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "Los gastos han sido registrados en el sistema!"));
                
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso",
                        "No ha sido posible registrar los gastos del documento en el sistema."));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "Seleccione el archivo para realizar el cargue masivo."));
        }
    }
    
    // Getters and setters

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public List<TipoGasto> getListaTiposGasto() {
        return listaTiposGasto;
    }

    public void setListaTiposGasto(List<TipoGasto> listaTiposGasto) {
        this.listaTiposGasto = listaTiposGasto;
    }

    public List<Gasto> getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(List<Gasto> listaGastos) {
        this.listaGastos = listaGastos;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
}
