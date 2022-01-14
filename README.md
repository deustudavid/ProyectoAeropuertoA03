# ProyectoAeropuertoA03
Nuestro proyecto de programación

Grupo A03- ProyectoAeropuerto. Instrucciones de uso.

Paso 1.Preparación de los .jar

Una vez descargado el proyecto, veremos varias directorios.Antes de nada fijémonos en el directorio "lib". Ahí se encuentran 3 .jar esenciales para el correcto funcionamiento d
tanto de la base de datos como de otras funcionalidades del proyecto. Se deben añadir estos .jar al build path del proyecto, y para ello los pasos son:
	1. Clic derecho sobre el proyecto. Se abrirá un menú desplegable con muchas opciones.
	2. Seleccionar Build Path> Configure BuildPath
	3. Situarse en Libraries
	4. Clic izquierdo sobre Modulepath>add JARs>ProyectoAeropuerto>lib>sqlite-jdbc-3.36.0.3.jar, así tendremos el driver de la base de datos configurado correctamente.
	5. Repetir el paso 4 para insertar otros dos .jar: itextpdf-5.5.13.2.jar y jcalendar-1.4.jar
	6. Realizar el paso 4, pero esta vez habiendo pulsado Classpath en vez de Modulepath, y añadir el jar flatlaf-2.0.jar, que modifica el aspecto visual de los componentes swing.
	7. Apply / Apply and close

Paso 2. Ejecucion del main del programa.
	Para ello, meterse en src> elegir el package main> seleccionar la clase VentanaInicio. Clic derecho sobre ésta>Run As>Java Application.
	Así se ejecuta el programa y está listo para usarse.

Paso 3. Iniciar sesión. Se puede iniciar sesión como azafato o como administrador. Si quieres acceder mediante el primer caso, usa el usuario: azafato y la contraseña: azafato
	De querer entrar como administrador, usa el administrador cuyo usuario y contraseña son admin y admin respectivamente.

Paso 4. Salir del programa.
	Seleccionando la opción del menú llamada Usuario, y posteriormente seleccionando la opción "Cerrar sesión". Esto lo envía de vuelta a la ventana de inicio de sesión,
	donde deberá pulsar sobre el botón "cerrar" para salir definitivamente.

El resto de funciones están en el menú de la ventana que se abra. Siendo administrador, dispones de más opciones que siendo azafato. También son los unicos que pueden añadir nuevos administradores, o contratar más azafatos.
Si usas ReservarTicket, tienes que empezar seleccionando un origen de vuelo y un destino de vuelo para filtrarlos. Si hay vuelos que cumplan esas condiciones, aparecerán en una tabla.Toca en la tabla el vuelo que quieres reservar.
Su información aparecerá a la derecha. Busca un pasajero introduciendo su dni (es una función que aparece en más sitios, por lo que se recomienda usar el pasajero de dni 00000000Q.
No olvides que se aplicarán tarifas extra al reservar un ticket solo si el pais de destino es diferente al de origen, y que se aplican descuentos automáticamente según la cantidad de asientos que se reserven.
Al insertar un nuevo pasajero, equipaje, etc. las expresiones regulares se encargarán de decirle al usuario si los datos que mete son correctos.
En la tabla de la ventana Ver Vuelos, se puede editar la hora de llegada/salida de un vuelo, pues pueden sufrir retrasos. Si al editarlo directamente desde la tabla se introduce una hora incorrecta, se pondrá la que estaba.
Al crear un pasajero, se puede crear sin añadir su fotografía. En ese caso, se le añadirá una fotografia especial, cuya ruta es fotos/empty.png

Directorio config: están los ficheros config.properties y logger.properties
Directorio img: están los iconos que se usan durante el proyecto para decorar.
Directorio fotos: hay varias fotos de pasajeros, que mediante su función correspondiente, se pueden visualizar y actualizar.
Directorio logs: ahí se generan los ficheros .log

Elementos de muestra en la base de datos: se han insertado un número considerable de azafatos, pasajeros, vuelos y equipajes a fin de proveer al usuario de una sensación de completitud
y coherencia a la hora de visualizar estos elementos en la tabla correspondiente, de querer hacerlo. Con ello se consigue que al seleccionar, por ejemplo, un pasajero, se puedan ver todos sus equipajes
y que parezca más realista. De todas formas, el programa permite no sólo visualizar cualquiera de esos elementos, sino de borrarlos también.
