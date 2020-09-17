package com.adsi.ensayapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_doc")
public class TipoDocumento implements Serializable {
    
    @Id
    @Column(name = "id_tipo_doc")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "abreviacion")
    private String abreviacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
    
    
}
