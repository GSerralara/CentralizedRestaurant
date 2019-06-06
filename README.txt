
AUTORES DEL PROYECTO

	David Diego
	Aleix Olle
	Victor Salvador
	Guillermo Serraclara
	Pablo Nogueras

----------------------------------------------------------------------------------------------------------------------

REQUISITOS

Los requisitos previos antes de utilizar nuestro programa es tener instalado el programa IntelliJ IDEA para poder ejecutar nuestro
programa. Por otro lado tambien sera necesario instalar el programa MySQLWorkblench que te permite conectarte a la SGBD (Sistema Gestor
de Base de Datos) .
Y por ultimo tendremos que ejecutar el archivo de SQL que estará dentro de la la carpeta Data Base.

----------------------------------------------------------------------------------------------------------------------


Centralizad Restaurant


Lo primero de todo antes de empezar a utilizar nuestro programa es necesario ejecutar una bbdd de MySQL. Dicho fichero se encontrara en
la Carpeta DataBase llamado "Restaurant—.sql", donde cargara toda la estructura de la base de datos.


INICIAR SERVIDOR

Primero de todo lo que tendremos que hacer será iniciar el Programa Servidor llamado "Centralizad Restaurant Server".
Una vez dentro de “ Centralizad Restaurant Server ” será necesario modificar el archivo "config.json" que se encuentra en la carpeta
"Data". Una vez que estamos en este fichero tendremos que cambiar la contraseña que viene por defecto y cambiarla por la que nosotros
tenemos. Todos los otros campos ya est‡n completos con sus respectivos datos.
A partir de estos ajustes que habremos realizado dentro del Servidor "Centralizad Restaurant Server" podremos ejecutarlo.


INICIAR SERVIDOR CLIENTE

Una vez que tengamos puesto en marcha el Servidor iniciaremos el Servidor Cliente llamado "Centralized Restaurant Client".
Aqu’ en la carpeta "Data", el archivo "config.json" lo dejaremos tal cual con la informacion que viene por defecto.

----------------------------------------------------------------------------------------------------------------------
OPCIONES CLIENTE


ACCESO/REGISTRO

Nos mostrara una pantalla donde tendremos que llenar diversos campos para acceder. Uno de estos campos será insertar los datos del
"Username" y luego rellenar los datos del campo "Password" con su respectiva contraseña. Por otro lado si se trata de un usuario que no
esta registrado tendra que clicar al boton "Register Now" donde tendra que rellenar sus respectivos campos.
Si todo esto se ha realizado con exito podremos acceder a la siguiente ventana grafica.
Las restricciones que tenemos en este apartado, es de que en el campo de la contraseña debe tener minimo 6 caracteres, tambien debe
tener minimo un numero y un car‡cter en Mayuscula. Si insertamos en el campo de contraseña todo numeros no nos funcionara.

Tambien tendrems que tener en cuenta que para acceder, tendremos que iniciar previamente el servicio en la opcion que encontramos
de "Centralizad Restaurant Server". Sino realizamos esto prreviamente no podremos accedder.

PEDIDOS

En esta pantalla grafica nos saldra todos los platos que el restaurante tiene disponible en todo momento. Aqui podremos realizar nuestras
respectivas comandas de los distintos platos que el restaurante tiene en todo momento. Ademas podremos seleccionar la cantidad de platos
que queremos realizar y si podemos pedir la cantidad que deseamos. A parte deseo podremos elegir varias opciones como es "Bill" y
"Order".

Otra opcion que hemos puesto es que una vez hagamos la reserva saldra un mensaje despues de 25 segundos, donde se nos confirmara la reserva.

PAGO

Una vez realizados los pedidos en la misma interfiere gr‡fica de los pedidos podremos realizar el pago de los platos consumidos clicando
al boton "Pay Bill".


----------------------------------------------------------------------------------------------------------------------

OPCIONES SERVIDOR


ESTADISTICAS

Aqui podremos ver a partir de un grafico, cuales han sido los platos mas consumidos durante un día o a partir de la inauguracion. Solo
mostraremos el TOP 5 de los platos mas consumidos.


MENU PRINCIPAL

Se nos abrirá una interfiere grafica que nos dará la bienvenida al programa. A partir de esta bienvenida tendremos que abrir un menu
desplegable donde podremos escoger diferentes opciones. Las opciones que se nos muestra son: Change State, To Current Service y
Authentication.


CHANGE STATE

En esta opcion podremos escoger cual es el estado de nuestro Servicio, es decir si Iniciamos el servicio, si lo tenemos en pausa o lo
hemos finalizado.
