CREATE PROCEDURE `obtener_numero_asistentes`(in in_id_evento int, out out_num_asis int)
BEGIN
	DECLARE ontador int DEFAULT 0;
    select COUNT(dni) INTO contador FROM asistentes_eventos where id_evento = in_id_evento;
    SET out_num_asis = contador;
END