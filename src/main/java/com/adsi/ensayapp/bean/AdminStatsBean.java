package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named
@ViewScoped
public class AdminStatsBean implements Serializable{
    
    Logger log = LogManager.getRootLogger();
    private Util util = new Util();
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    private DonutChartModel donutModel;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init(){
        createDonutModel();
        createBarModel();
    }
    
    public void createDonutModel() {
        
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        
        List<String> labels = new ArrayList<>();
        List<Number> values = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();
        
        List<Object[]> reservations = reservacionEJB.getReservationsNumberByRoom();
        int i = 0;
        for (Object[] reservation : reservations) {
            //log.info("Estado: "+reservation[0]+" Cantidad: "+reservation[1]);
            values.add((long) reservation[1]);
            bgColors.add(util.getBackgroundColor());
            labels.add((String) reservation[0]);
            
            i++;
        }
        
        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColors);
        
         
        data.addChartDataSet(dataSet);
        data.setLabels(labels);       
         
        donutModel.setData(data);
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
        
        List<Object[]> reservations = reservacionEJB.getReservationsByState();
        int i = 0;
        for (Object[] reservation : reservations) {
            values.add((long) reservation[1]);
            bgColor.add(util.getBackgroundColor());
            labels.add((String) reservation[0]);
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
        title.setText("Reservaciones por estado");
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
    
    
    
    // Getters and setters
    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    
}
