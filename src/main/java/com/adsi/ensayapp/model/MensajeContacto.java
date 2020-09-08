package com.adsi.ensayapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_contacto")
public class MensajeContacto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje_contacto")
    private long id;
    
    @Column(name = "nombre")
    private String nombre = "";
    
    @Column(name = "apellido")
    private String apellido = "";
    
    @Column(name = "correo")
    private String correo = "";
    
    @Column(name = "mensaje")
    private String mensaje = "";
    
    @Column(name = "asunto")
    private String asunto = "";
    
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
    
    @Column(name = "estado_registro")
    private String estado;

    public MensajeContacto(){
        fechaRegistro = new Timestamp(System.currentTimeMillis());
        estado = "Activo";
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
