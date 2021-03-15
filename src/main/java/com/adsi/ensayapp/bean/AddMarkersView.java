
package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.ejb.SucursalFacadeLocal;
import com.adsi.ensayapp.model.Sala;
import com.adsi.ensayapp.model.Sucursal;
import com.adsi.ensayapp.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    
    private Sucursal sucursal;
    
    private Usuario musico;
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @EJB
    private SucursalFacadeLocal sucursalEJB;
    
    private List<Sucursal> listaSucursales;
    
    private List<Sala> salas;
    
    private Sala sala;
    
    Logger log = LogManager.getRootLogger();
  
    @PostConstruct
    public void init() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        musico = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        salas = buscarListaSalas();
        
        emptyModel = new DefaultMapModel();
        listaSucursales = sucursalEJB.findAll();
        for (Sucursal sucursal : listaSucursales) {
            Marker m;
            m = new Marker(new LatLng(Double.parseDouble(sucursal.getLat()), 
                    Double.parseDouble(sucursal.getLng())),
                    sucursal.getIdSucursal()+"-"+sucursal.getNombre());
            
            m.setData(sucursal.getDireccion());
            m.setIcon("../../../resources/img/MarkerIcon_preview_rev_1.png");
            m.setId(String.valueOf(sucursal.getIdSucursal()));
            
            emptyModel.addOverlay(m);
        }
        
        sucursal = new Sucursal();
    }
      
    public List<Sala> buscarListaSalas(){
        List<Sala> listado = null;
        try {
            listado = salaEJB.findAllActiveRooms();
        } catch (Exception e) {
            throw e;
        }
        return listado;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        System.out.println("Seleccion de marca!");
        marker = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
        String[] sucursalArray = marker.getTitle().split("-");
        sucursal = sucursalEJB.find(Long.parseLong(sucursalArray[0]));
    }
    
    public void openRooms(){
        System.out.println("Ver salas de la sucursal: "+marker.getTitle());
        String[] sucursal = marker.getTitle().split("-");
        System.out.println("Ver salas de la sucursal con id: "+sucursal[0]);
    }
    
    public void consultarSalas(){
       
        log.info("Buscar salas de sucursal seleccionada del mapa!");
        
        try {
            System.out.println("Consultar salas de ensayo de la sucursal: "+sucursal.getIdSucursal());
            
            if (sucursal.getIdSucursal() != 0) {
                salas = salaEJB.findAllByBranchOffice(sucursal.getIdSucursal());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "Seleccione una sucursal para consultar las salas de ensayo"));
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "Ha ocurrido un error: "+e.getMessage()));
        }
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getMusico() {
        return musico;
    }

    public void setMusico(Usuario musico) {
        this.musico = musico;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
      
    
}
