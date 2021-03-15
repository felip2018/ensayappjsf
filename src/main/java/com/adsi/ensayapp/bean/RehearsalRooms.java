package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.RoomValidationResponseDTO;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.ejb.SistemaHoraFacadeLocal;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Sala;
import com.adsi.ensayapp.model.SistemaHora;
import com.adsi.ensayapp.model.Usuario;
import com.adsi.ensayapp.model.Sucursal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@Named
@ViewScoped
public class RehearsalRooms implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Logger log = LogManager.getRootLogger();
    
    private List<Sala> salas;
    private Sala sala;
    private Reservacion reservacion;
    private String paramId;
    private Usuario musico;
    private Sucursal sucursal;
    
    private Date currentDate;
    
    @EJB
    private SalaFacadeLocal salaEJB;
    
    @EJB
    private ReservacionFacadeLocal reservacionEJB;
    
    @EJB
    private SistemaHoraFacadeLocal sistemaHoraEJB;
    
    private List<SistemaHora> listaHoras;
        
    @PostConstruct
    public void init(){
        
        Date dt = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        c.add(Calendar.DATE, 1);
        currentDate = c.getTime();
        
        FacesContext context = FacesContext.getCurrentInstance();
        musico = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        salas = buscarListaSalas();
        reservacion = new Reservacion();
        reservacion.setIdUsuario(musico.getId());
        reservacion.setIdEstadoReserva(1);
        reservacion.setCalificacion(0);
        reservacion.setFechaRegistro(new Date());
        reservacion.setEstadoRegistro("Activo");
        
        sucursal = new Sucursal();
        
        listaHoras = sistemaHoraEJB.findAllByState();
    }
    
    public void actualizarInformacionSala(){
        try {
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarSala(){
        try {
            sala.setEstadoRegistro("Inactivo");
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void activarSala(){
        try {
            sala.setEstadoRegistro("Activo");
            salaEJB.edit(sala);
        } catch (Exception e) {
            throw e;
        }
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
    
    public void detalleSala(){
        try {
            sala = salaEJB.find(Integer.parseInt(paramId));
            reservacion.setIdSala(sala.getId());
        } catch (NumberFormatException e) {
            log.error("ERROR:"+e.getMessage());
        }
    }
    
    public void realizarReservacion(){
        try {
            Long validation = reservacionEJB.countActiveReservations(reservacion.getIdUsuario());
            log.info("Validacion de reservas activas: "+validation);
            
            if (validation == 0) {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String fechaReserva = simpleDateFormat.format(reservacion.getFecha());
                
                RoomValidationResponseDTO roomDisponibility = reservacionEJB.validacionSalaEnsayo(reservacion);
                
                if(roomDisponibility.getCant() == 0){
                    
                    reservacion.setIdSala(sala.getId());
                    reservacion.setPrecio(sala.getPrecio());
                    reservacionEJB.create(reservacion);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Aviso",
                            "Se ha realizado la reservación de la sala de ensayo."));
                } else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    roomDisponibility.getMensaje()));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "El usuario ya cuenta con una reservacion activa."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Aviso",
                    "Ha ocurrido un error: "+e.getMessage()));
        }
    }
    
    public void consultarSalas(){
       
        log.info("Evento de cambio de sala: ");
        
        try {
            //System.out.println("Consultar salas de ensayo de la sucursal: "+sucursal.getIdSucursal());
            
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

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public Usuario getMusico() {
        return musico;
    }

    public void setMusico(Usuario musico) {
        this.musico = musico;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    public Date getCurrentDate(){
        return currentDate;
    }

    public List<SistemaHora> getListaHoras() {
        return listaHoras;
    }

    public void setListaHoras(List<SistemaHora> listaHoras) {
        this.listaHoras = listaHoras;
    }
    
}
