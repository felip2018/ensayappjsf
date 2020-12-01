package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ActivoFacadeLocal;
import com.adsi.ensayapp.model.Activo;
import com.adsi.ensayapp.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named
@ViewScoped
public class InstrumentBean implements Serializable {
    
    Logger log = LogManager.getRootLogger();
    private final Util util = new Util();
    
    @EJB
    private ActivoFacadeLocal activoEJB;
    
    private Activo activo;
    
    private List<Activo> listadoActivos;
    
    private List<Object[]> listaActivosEstado;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init(){
        listadoActivos = activoEJB.findAll();
        activo = new Activo();
        activo.setEstadoActivo("EN BODEGA");
        activo.setFechaRegistro(new Date());
        activo.setEstadoRegistro("Activo");
        
        createBarModel();
    }
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("");
        
        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        List<Object[]> activos = activoEJB.getAssetsByCurrentState();
        int i = 0;
        for (Object[] a : activos) {
            values.add((long) a[1]);
            bgColor.add(util.getBackgroundColor());
            labels.add((String) a[0]);
        }
        
        barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColor);
         
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        
        barModel.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(false);
        title.setText("Activos por estado");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        barModel.setOptions(options);
    }
    
    public void registrarActivo(){
        try {
            
            activoEJB.create(activo);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Aviso",
                    "El activo ha sido registrado correctamente!"));
            activo = new Activo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Aviso",
                    "No ha sido posible registrar el activo."));
        }
    }
    
    
    
    // Getters and setters

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public List<Activo> getListadoActivos() {
        return listadoActivos;
    }

    public void setListadoActivos(List<Activo> listadoActivos) {
        this.listadoActivos = listadoActivos;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
}
