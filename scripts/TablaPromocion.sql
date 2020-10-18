CREATE TABLE promocion(
	id_promocion INT(11) PRIMARY KEY AUTO_INCREMENT,
    id_establecimiento INT(11) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    imagen VARCHAR(100),
    fecha_registro DATETIME,
    estado_registro VARCHAR(45)
);

ALTER TABLE promocion ADD FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento);