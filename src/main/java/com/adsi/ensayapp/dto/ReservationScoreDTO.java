package com.adsi.ensayapp.dto;

import com.adsi.ensayapp.model.Reservacion;

public class ReservationScoreDTO {
    
    private Reservacion reservacion;
    
    private int sonido;
    private int instalaciones;
    private int puntualidad;
    private int equipos;
    
    public ReservationScoreDTO(){
        this.sonido = 5;
        this.instalaciones = 5;
        this.puntualidad = 5;
        this.equipos = 5;
    }
    
    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(int instalaciones) {
        this.instalaciones = instalaciones;
    }

    public int getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(int puntualidad) {
        this.puntualidad = puntualidad;
    }

    public int getEquipos() {
        return equipos;
    }

    public void setEquipos(int equipos) {
        this.equipos = equipos;
    }
    
    
    
}
