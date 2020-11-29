package com.adsi.ensayapp.dto;

public class ChargeInfoDTO {

    private double monto;
    private String fechaRegistro;
    private String fechaReservacion;
    private String sala;
    private String tipoRecargo;
    private String usuario;
    private String estadoRecargo;
    private String identificacion;
    private int idRecargo;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getTipoRecargo() {
        return tipoRecargo;
    }

    public void setTipoRecargo(String tipoRecargo) {
        this.tipoRecargo = tipoRecargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstadoRecargo() {
        return estadoRecargo;
    }

    public void setEstadoRecargo(String estadoRecargo) {
        this.estadoRecargo = estadoRecargo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getIdRecargo() {
        return idRecargo;
    }

    public void setIdRecargo(int idRecargo) {
        this.idRecargo = idRecargo;
    }

}
