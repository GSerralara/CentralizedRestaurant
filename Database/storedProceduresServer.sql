Use Restaurant;

-- Stored procedure per login usuari
DELIMITER $$

DROP PROCEDURE IF EXISTS loginRestaurant $$

CREATE PROCEDURE loginRestaurant (IN email_in VARCHAR(255), IN password_in VARCHAR(255), OUT ok INTEGER)
BEGIN
	DECLARE done INT DEFAULT 0;
    DECLARE trobat_username BOOLEAN DEFAULT FALSE;
    DECLARE identificador VARCHAR(255);
	DECLARE identificador2 VARCHAR(255);
    DECLARE identificador_3 VARCHAR(255);
    DECLARE cur1 CURSOR FOR SELECT username FROM restaurant;
	DECLARE cur2 CURSOR FOR SELECT email FROM restaurant;
    DECLARE cur3 CURSOR FOR SELECT password FROM restaurant;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
	SET ok = 0;
    
    OPEN cur1;
    OPEN cur2;
	OPEN cur3;
    
trobar_username: LOOP
		FETCH cur1 INTO identificador;
		FETCH cur2 INTO identificador2;
		FETCH cur3 INTO identificador_3;
        
		IF done = 1 THEN 
            LEAVE trobar_username;
		END IF;
        
		IF (identificador = email_in OR identificador2 = email_in) AND identificador_3 = password_in THEN
			SET trobat_username = TRUE;
			LEAVE trobar_username;
		END IF;
END LOOP trobar_username;

CLOSE cur1;
CLOSE cur2;
CLOSE cur3;
IF trobat_username = TRUE THEN
	SET ok = 1;
    SELECT ok;
ELSE 
	SELECT("Usuari o contrasenya incorrecte");
END IF;
END $$

DELIMITER ;

-- Stored procedure per registre usuari
DELIMITER $$

DROP PROCEDURE IF EXISTS newRestaurant $$

CREATE PROCEDURE newRestaurant (IN email_in VARCHAR(255), IN username_in VARCHAR(255), IN password_in VARCHAR(255), OUT ok INTEGER)
BEGIN
	DECLARE done INT DEFAULT 0;
    DECLARE trobat_username BOOLEAN DEFAULT FALSE;
    DECLARE trobat_email BOOLEAN DEFAULT FALSE;
    DECLARE identificador VARCHAR(255);
    DECLARE cur1 CURSOR FOR SELECT username FROM restaurant;
	DECLARE cur2 CURSOR FOR SELECT email FROM restaurant;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
	SET ok = 1;
    
    OPEN cur1;
    OPEN cur2;
    
trobar_username: LOOP
		FETCH cur1 INTO identificador;
        IF done = 1 THEN 
            LEAVE trobar_username;
		END IF;
       IF identificador = username_in THEN
			SET trobat_username = TRUE;
			LEAVE trobar_username;
		END IF;
END LOOP trobar_username;
CLOSE cur1;
	IF trobat_username = TRUE THEN
		SET ok = 0;
	END IF;
    set done = 0;
trobar_email: LOOP
		FETCH cur2 INTO identificador;
        IF done = 1 then 
			LEAVE trobar_email;
		END IF;
        IF identificador = email_in THEN
			SET trobat_email = TRUE;
            LEAVE trobar_email;
		END IF;
END LOOP trobar_email;
		SELECT ok;
	IF ok = 1 AND trobat_email = FALSE THEN
		SELECT ok;
		INSERT INTO restaurant VALUES (email_in,username_in,password_in);
	END IF;
 CLOSE cur2;   
END $$

DELIMITER ;
