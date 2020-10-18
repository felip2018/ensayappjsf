package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.model.Sala;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
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
public class RegisterRoomForm implements Serializable {

    Logger log = LogManager.getRootLogger();

    @EJB
    private SalaFacadeLocal salaEJB;

    private Sala sala;

    private UploadedFile file;

    @PostConstruct
    public void init() {
        sala = new Sala();
        sala.setIdSucursal(1);
        sala.setFechaRegistro(new Date());
        sala.setEstadoRegistro("Activo");
    }

    public void registrarSala() {
        try {
            salaEJB.create(sala);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "La sala de ensayo ha sido registrada!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "No ha sido posible registrar la sala de ensayo."));
        }
    }

    public void cargarArchivo() throws IOException {
        if (file != null) {
            try {
                InputStream input = file.getInputStream();

                XSSFWorkbook workbook = new XSSFWorkbook(input);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    Sala nuevaSala = new Sala();
                    Row currentRow = iterator.next();

                    if (i > 0) {

                        if (currentRow.getCell(0) != null
                                && currentRow.getCell(1) != null
                                && currentRow.getCell(2) != null) {

                            nuevaSala.setIdSucursal(1);
                            nuevaSala.setNombre(currentRow.getCell(0).getStringCellValue());
                            nuevaSala.setPrecio(currentRow.getCell(1).getNumericCellValue());
                            nuevaSala.setDescripcion(currentRow.getCell(2).getStringCellValue());
                            nuevaSala.setFechaRegistro(new Date());
                            nuevaSala.setEstadoRegistro("Activo");

                            salaEJB.create(nuevaSala);
                        } else {
                            log.info("No more data!");
                            break;
                        }
                    }
                    i++;
                }
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "La salas de ensayo han sido registradas!"));
                
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso",
                        "No ha sido posible registrar la sala de ensayo."));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "Seleccione el archivo para realizar el cargue masivo."));
        }
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
