
AUTORES DEL PROYECTO
	David Diego
	Aleix Olle
	Victor Salvador
	Guillermo Serraclara
----------------------------------------------------------------------------------------------------------------------

REQUISITOS

Los requisitos previos antes de utilizar nuestro programa es tener instalado JAVA,
opcionalmente para compilar todo el trabajo puedes usar un IDE de trabajo (en nuestro caso IntelliJ IDEA)
Por otro lado tambien sera necesario instalar la SGBD (Sistema Gestor
de Base de Datos) de MySQL que te permite conectarte a  la bbdd del programa, una vez haya sido crada.
Y por ultimo tendremos que compilar los scripts SQL que estarán dentro de la la carpeta Data Base.

----------------------------------------------------------------------------------------------------------------------


Centralizad Restaurant


Lo primero de todo antes de empezar a utilizar nuestro programa, una vez cumplidos todos los requisitos. Es ir al archivo de "config.json",
que se encuentra en la carpeta "Data" de cada programa, y configurarlo segun tus necesidades.
----------------------------------------------------------------------------------------------------------------------
SERVIDOR


Primero de todo, cuando arranques la ventana grafica, te saldra que tienes que registrarte e iniciar session. Una vez iniciada,
tendras que abrir el panel oculto con 3 opciones.
	->Cambiar estado : que nos perimite cambiar el estado
	->Servicio Actual : la ventana que cambia segun el estado
	->Autorizaciones : los pedidos de reserva del restaurante
Tendras 3 opciones:
	->Iniciar : Preservicio y Servicio, si hemos aceptado alguna autorizacion sera la segunda, la ventana de servicio actual
	->Parar	: Postservicio, la ventana de servicio actual cambiara a una que nos ofrece estadisticas
	->Cerrar : No servicio, la ventana de servicio actual cambiara a una que nos invita a cerrar sesion
Empezaras en iniciar, cambiando asi la ventana asociada al servicio actual a preservicio.
	Preservicio: Es gestion de mesas, platos y cartas
	Servicio: Es la gestion de comandas
----------------------------------------------------------------------------------------------------------------------
CLIENTE


Primero de todo, cuando arranques la ventana grafica, te saldra que tienes que registrarte e iniciar session. Una vez iniciada,
se nos daran 3 opciones:
	->Reservar : Que llenaras un formulario
	->Cancelar : Que cancelaras una reserva
	->Log Out : Que cerraras la sesion 
Si nos aceptan la reserva, podremos iniciar sesion con el nombre de la reserva que teniamos, introduciendo a su vez
la contraseña que tenemos por medidas de seguridad.
Una vez inicada la sesion de reserva, nos saldra 3 pestañas:
	->Menu: Lista de platos acutalizada del servidor
	->Order: Nuestos predidos, que podremos siempre cancelarlos siempre que esten pedidos
	->Bill: Factura, con opcion de pagar e irte del restaurante.
----------------------------------------------------------------------------------------------------------------------