package com.adsi.ensayapp.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-28T19:12:03")
@StaticMetamodel(MensajeContacto.class)
public class MensajeContacto_ { 

    public static volatile SingularAttribute<MensajeContacto, String> estado;
    public static volatile SingularAttribute<MensajeContacto, Timestamp> fechaRegistro;
    public static volatile SingularAttribute<MensajeContacto, String> apellido;
    public static volatile SingularAttribute<MensajeContacto, String> correo;
    public static volatile SingularAttribute<MensajeContacto, String> asunto;
    public static volatile SingularAttribute<MensajeContacto, Long> id;
    public static volatile SingularAttribute<MensajeContacto, String> mensaje;
    public static volatile SingularAttribute<MensajeContacto, String> nombre;

}