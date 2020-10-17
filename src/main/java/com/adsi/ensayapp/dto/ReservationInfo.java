package com.adsi.ensayapp.dto;

import com.adsi.ensayapp.model.Sala;
import java.time.LocalDateTime;

public class ReservationInfo {
    
    private LocalDateTime inicio;
    private LocalDateTime finalizacion;
    private Sala sala;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(LocalDateTime finalizacion) {
        this.finalizacion = finalizacion;
    }
    
    
    
}
