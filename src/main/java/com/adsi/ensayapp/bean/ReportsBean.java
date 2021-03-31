package com.adsi.ensayapp.bean;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

@ManagedBean
@RequestScoped
public class ReportsBean {
    
    private String dbUrl = "jdbc:mysql://localhost:3306/ensayapp_db?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUser = "root";
    private String dbPass = "Felipegarzon2021*";
    
    public void verPDF(ActionEvent actionEvent) throws Exception {
        
        try {
            Connection connection = (Connection) DriverManager.getConnection(dbUrl,dbUser,dbPass);
            File jasper = new File("C:/EnsayappFormats/Reporte_Reservaciones.jasper");

            //byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null);
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, connection);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error::: "+e.getMessage());
        }
    }

}
