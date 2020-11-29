package com.adsi.ensayapp.bean;

import com.adsi.ensayapp.dto.ReservationInfo;
import com.adsi.ensayapp.ejb.ReservacionFacadeLocal;
import com.adsi.ensayapp.ejb.SalaFacadeLocal;
import com.adsi.ensayapp.ejb.SistemaHoraFacadeLocal;
import com.adsi.ensayapp.model.Reservacion;
import com.adsi.ensayapp.model.Sala;
import com.adsi.ensayapp.model.SistemaHora;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named
@ViewScoped
public class ScheduleJava8View implements Serializable {

    Logger log = LogManager.getRootLogger();

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private boolean showWeekends = true;
    private boolean tooltip = true;
    private boolean allDaySlot = true;

    private String timeFormat;
    private String slotDuration = "00:30:00";
    private String slotLabelInterval;
    private String scrollTime = "06:00:00";
    private String minTime = "04:00:00";
    private String maxTime = "20:00:00";
    private String locale = "en";
    private String timeZone = "";
    private String clientTimeZone = "local";
    private String columnHeaderFormat = "";

    //Variables del proyecto
    @EJB
    private ReservacionFacadeLocal reservacionEJB;

    @EJB
    private SistemaHoraFacadeLocal sistemaHoraEJB;

    @EJB
    private SalaFacadeLocal salaEJB;

    private Reservacion reservacionSeleccionada;
    private List<Reservacion> listaReservaciones;

    @PostConstruct
    public void init() {
        reservacionSeleccionada = new Reservacion();
        eventModel = new DefaultScheduleModel();
        
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        
        listaReservaciones = reservacionEJB.getReservationsByStates(ids);
        for (Reservacion reserva : listaReservaciones) {

            ReservationInfo reservationInfo = getReservationInfo(reserva);
            
            if (reservationInfo!=null) {
                event = DefaultScheduleEvent.builder()
                        .title(reservationInfo.getSala().getNombre())
                        .startDate(reservationInfo.getInicio())
                        .endDate(reservationInfo.getFinalizacion())
                        .description(reserva.getComentarios())
                        .overlapAllowed(true)
                        .data(reserva.getIdReservacion())
                        .build();
                eventModel.addEvent(event);
                event = null;
            }
        }
    }

    public ReservationInfo getReservationInfo(Reservacion reserva) {
        ReservationInfo reservationInfo = new ReservationInfo();
        LocalDateTime date;
        String[] horaInicio;
        String[] horaFinalizacion;
        try {
            Sala sala = salaEJB.find(reserva.getIdSala());
            SistemaHora sistemaHora = sistemaHoraEJB.find(reserva.getIdSistemHora());
            horaInicio = sistemaHora.getHoraInicio().split(":");
            horaFinalizacion = sistemaHora.getHoraFinalizacion().split(":");

            date = reserva.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    .withHour(Integer.parseInt(horaInicio[0]))
                    .withMinute(Integer.parseInt(horaInicio[1]))
                    .withSecond(Integer.parseInt(horaInicio[2]));

            reservationInfo.setInicio(date);

            date = reserva.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    .withHour(Integer.parseInt(horaFinalizacion[0]))
                    .withMinute(Integer.parseInt(horaFinalizacion[1]))
                    .withSecond(Integer.parseInt(horaFinalizacion[2]));

            reservationInfo.setFinalizacion(date);
            reservationInfo.setSala(sala);
            
        } catch (Exception e) {
            log.info("Error---");
            reservationInfo = null;
        }

        return reservationInfo;
    }

    public void onEventSelect(SelectEvent<ScheduleEvent> selectEvent) {
        event = selectEvent.getObject();
        reservacionSeleccionada = reservacionEJB.find(event.getData());
        log.info("Evento seleccionado: "+event.getTitle()+", Id Reserva: "+reservacionSeleccionada.getIdReservacion());
    }
    
    public void updateEvent(){
        log.info("Actualizar reservacion: "+reservacionSeleccionada.getIdEstadoReserva());
        reservacionEJB.edit(reservacionSeleccionada);
        
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        listaReservaciones = reservacionEJB.getReservationsByStates(ids);
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = DefaultScheduleEvent.builder().startDate(selectEvent.getObject()).endDate(selectEvent.getObject().plusHours(1)).build();
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Delta:" + event.getDeltaAsDuration());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

        addMessage(message);
    }
    
    
    
    // Getters and setters
    
    public Reservacion getReservacionSeleccionada() {    
        return reservacionSeleccionada;
    }

    public void setReservacionSeleccionada(Reservacion reservacionSeleccionada) {
        this.reservacionSeleccionada = reservacionSeleccionada;
    }

    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    
    public void setListaReservaciones(List<Reservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public LocalDateTime getRandomDateTime(LocalDateTime base) {
        LocalDateTime dateTime = base.withMinute(0).withSecond(0).withNano(0);
        return dateTime.plusDays(((int) (Math.random() * 30)));
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public LocalDateTime getInitialDate() {
        return LocalDateTime.now().plusDays(1);
    }

    public ScheduleEvent getEvent() {
        return event;
    }
    
    public boolean isShowWeekends() {
        return showWeekends;
    }

    public void setShowWeekends(boolean showWeekends) {
        this.showWeekends = showWeekends;
    }

    public boolean isTooltip() {
        return tooltip;
    }

    public void setTooltip(boolean tooltip) {
        this.tooltip = tooltip;
    }

    public boolean isAllDaySlot() {
        return allDaySlot;
    }

    public void setAllDaySlot(boolean allDaySlot) {
        this.allDaySlot = allDaySlot;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(String slotDuration) {
        this.slotDuration = slotDuration;
    }

    public String getSlotLabelInterval() {
        return slotLabelInterval;
    }

    public void setSlotLabelInterval(String slotLabelInterval) {
        this.slotLabelInterval = slotLabelInterval;
    }

    public String getScrollTime() {
        return scrollTime;
    }

    public void setScrollTime(String scrollTime) {
        this.scrollTime = scrollTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getClientTimeZone() {
        return clientTimeZone;
    }

    public void setClientTimeZone(String clientTimeZone) {
        this.clientTimeZone = clientTimeZone;
    }

    public String getColumnHeaderFormat() {
        return columnHeaderFormat;
    }

    public void setColumnHeaderFormat(String columnHeaderFormat) {
        this.columnHeaderFormat = columnHeaderFormat;
    }
}
