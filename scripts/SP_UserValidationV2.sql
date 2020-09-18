DELIMITER $$
CREATE PROCEDURE SP_UserValidationV2(idTipoDoc INT, 
	numDoc DOUBLE, 
	correoElectronico VARCHAR(100), 
	OUT cant INT, 
	OUT mensaje VARCHAR(200)
)

BEGIN
    SELECT count(*) INTO cant FROM usuario WHERE id_tipo_doc = idTipoDoc AND num_doc = numDoc;
    IF (cant > 0) THEN
		SET mensaje = "Ya existe un usuario registrado con la identificación.";
	ELSE
		SELECT count(*) INTO cant FROM usuario WHERE correo LIKE(correoElectronico);
        IF (cant > 0) THEN
			SET mensaje = "El correo electrónico ya esta en uso.";
        END IF;
	END IF;  
    
END $$
DELIMITER ;

CALL SP_UserValidationV2(1,12345,'felipe@gmail.com',@cant,@mensaje);
SELECT @cant,@mensaje;

