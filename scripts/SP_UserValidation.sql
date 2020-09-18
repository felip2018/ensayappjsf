DELIMITER $$
CREATE PROCEDURE SP_UserValidation(idTipoDoc INT, numDoc DOUBLE, correoElectronico VARCHAR(100),OUT cant INT, OUT mensaje VARCHAR(200))
BEGIN    
    SELECT count(*) INTO cant FROM usuario WHERE id_tipo_doc = idTipoDoc AND num_doc = numDoc;
    IF (cant > 0) THEN
		SET mensaje = "Ya existe un usuario registrado con el tipo y número de identificación ingresados.";
	ELSE
		SELECT count(*) INTO cant FROM usuario WHERE correo LIKE(correoElectronico);
        IF (cant > 0) THEN
			SET mensaje = "El correo electrónico ya se encuentra registrado.";
		ELSE
			SET mensaje = "Es posible realizar el registro";
        END IF;
	END IF;  
END $$
DELIMITER ;

CALL SP_UserValidation(1,12345,'felipe@gmail.com',@cant,@mensaje);
SELECT @cant,@mensaje;