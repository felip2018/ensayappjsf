
package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.ejb.SucursalFacadeLocal;
import com.adsi.ensayapp.model.Sucursal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@ViewScoped
public class AddMarkersView implements Serializable {
     
    private MapModel emptyModel;
      
    private String title;
      
    private double lat;
      
    private double lng;
    
    private Marker marker;
    
    @EJB
    private SucursalFacadeLocal sucursalEJB;
    
    private List<Sucursal> listaSucursales;
  
    @PostConstruct
    public void init() {
        
        emptyModel = new DefaultMapModel();
        listaSucursales = sucursalEJB.findAll();
        for (Sucursal sucursal : listaSucursales) {
            Marker marker = new Marker(new LatLng(Double.parseDouble(sucursal.getLat()), 
                    Double.parseDouble(sucursal.getLng())), 
                    sucursal.getNombre());
            marker.setIcon("../../../resources/img/MarkerIcon_preview_rev_1.png");
            emptyModel.addOverlay(marker);
        }
    }
      
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }
    
    
    public void addMarker() {
        System.out.println("Add Marker Function()-> Lat: "+lat+", Lng: "+lng);
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    
    
    // Getters and setters
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    public String getTitle() {
        return title;
    }
  
    public void setTitle(String title) {
        this.title = title;
    }
  
    public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<Sucursal> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }
      
    
}
