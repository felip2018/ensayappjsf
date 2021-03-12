DELIMITER $$
CREATE PROCEDURE SP_ReservationValidation(idSala INT, idSistemaHora INT, fechaReserva DATE, OUT cant INT, OUT mensaje VARCHAR(200))
BEGIN    
    SELECT count(*) INTO cant FROM reservacion WHERE id_sala = idSala AND id_sistema_hora = idSistemaHora AND fecha = fechaReserva;
    IF (cant > 0) THEN
		SET mensaje = "No es posible realizar esta reservación: no existe disponibilidad de la sala en la fecha y hora seleccionados";
	ELSE
		SET mensaje = "Si existe disponibilidad para la reserva";
	END IF;  
END $$
DELIMITER ;

CALL SP_ReservationValidation(1,1,'2021-03-12',@cant,@mensaje);
SELECT @cant,@mensaje;