# Inventario Joyeria Inventario

Este repositorio contiene un proyecto de joyeriaInventario el cual tiene el fin de realizar un CRUD simple para joyas y realización de compras con cliente, junt con una actualización de stock, además cuenta con opciones para generar tanto una factura usando iText y reportes en excel con Apache POI

# Especificaciones del proyecto

>[!IMPORTANTE]
El proyecto utiliza diferentes herramientras para generar archivos en excel y en PDF consultar la documentación de apache POI  [Documentación Apache POI](https://poi.apache.org/apidocs/index.html) y iText[API iText Documentacion](https://itextpdf.com/resources/api-documentation) ya que en ciertas versiones de ambas librerias cambian ciertos aspectos para generar los archivos.

Adicionalmente este proyecto se llevo a cabo con las diferentes herramientas:

- [ ] Java 21
- [ ] Apache Netbeans IDE 24 + librerias
- [ ] iText 5 [Archivo jar para crear PDF](https://repo1.maven.org/maven2/com/itextpdf/itextpdf/5.5.13.2/itextpdf-5.5.13.2.jar)
- [ ] Apache POI 3.7 [Archivo en GitHub](https://github.com/estebanArmonica/JoyeriaInventario/tree/main/dependecias)
- [ ] PostgreSQL Conector 42.7.5 [Archivo en GitHub](https://github.com/estebanArmonica/JoyeriaInventario/tree/main/dependecias)
- [ ] pgAdmin 17

# Levantamiendo del Backup en PostgreSQL
el proyecto esta creado con bases de datos PostgreSQL, para esto existe un archivo llamada [respaldo.backup](https://github.com/estebanArmonica/JoyeriaInventario/tree/main/Ejecutable%20y%20bakup), este Backup es que tiene que utilizar en pgAdmin para restaurar la base de datos.

- Primero en pgAdmin cre un nuevo `Owner` llamado `esteban` con la contraseña `armonica`, dependiendo de la configuarión se le crean los privileguios `por defecto el usuario es super user`
![fotito](https://github.com/user-attachments/assets/a1b75876-724e-4469-9a1a-96514c9e966a)

![imagen](https://github.com/user-attachments/assets/1bca28cd-e138-46a0-9b48-597d0132def9)

![imagen](https://github.com/user-attachments/assets/c3fd91dd-06d0-43cb-a442-ea8cf0686e3e)

- cree una nueva base de datos llamada `inventario`, por defecto esta en el puerto `5432`, recuerde en `database()` al hacer click dereche y en CREATE -> Database, aparecera esto
  ![imagen](https://github.com/user-attachments/assets/281bce03-9087-4491-ad51-02b84f3eeb66)
- coloque solamente guardar y listo.
- Luego hace click derecho en la base de datos inventario y buscar "BACKUP", al apretar ese apartado aparecera esta ventana
  ![imagen](https://github.com/user-attachments/assets/527a981e-7fa7-4035-977c-8589860b4e1e)

  Recomendación: el Format dejarlo como Custom ya que el archivo Backup es Custom `fue generado por comandos pg_dump`
  En Filename buscar el archivo llamado `respaldo.backup` y le damos en aceptar.
  Los demas puntos no es necesario tocarlos, una vez ya encontrado el archivo backup, le damos en boton "Backup" y mostrara una alerta de que el backup fue restaurado con éxito.
- Despues de eso solo debemos realizar un "Refresh" y listo, podemos realizar querys para verificar los datos de respaldo y utilizar el ejecutador `joyeriaInventario.jar`


# Contribución

Siéntase libre de enviar o expresar un feedback sobre el proyecto.


# Problemas

Puntos faltantes (que no se pudieron lograr)

3️⃣ Reporte Básico de Ventas


✅ Joyas más vendidas.
✅ Clientes con más compras.

se pudo crear la lista de las ventas pero estos tres puntos no se lograron concretar por problemas en el código, al final no las agregue.

🔹 Extras Opcionales
🔥 Búsqueda y filtrado avanzado por material, precio

realice solo una que fue el de cliente, tiene búsqueda por cliente, material tambien pero no por tabla si no más bien para borrar una joya.

Puntos logrados

✅ Arquitectura MVC (Modelo-Vista-Controlador).
✅ POO con clases JoyaVO, ClienteVO, VentaVO.
✅ DAO para la gestión de la base de datos en PostgreSQL.
✅ Java Swing para la interfaz gráfica con JTable.
✅ Conexión con PostgreSQL utilizando JDBC y PreparedStatement.
✅ Validaciones y manejo de excepciones.

📌 Funcionalidades

1️⃣ Inventario de Joyas (CRUD)

✅ Registrar nuevas joyas (nombre, material, peso, precio, stock).
✅ Listar joyas disponibles en JTable.
✅ Actualizar datos de una joya (precio, stock, etc.).
✅ Eliminar joyas del sistema.

2️⃣ Módulo de Ventas

✅ Registrar una venta seleccionando un cliente y joya.
✅ Validar stock antes de vender (si no hay suficiente stock, mostrar error).
✅ Actualizar stock tras cada venta.
✅ Mostrar ventas en JTable con filtros por cliente o fecha.

3️⃣ Reporte Básico de Ventas
✅ Total de ventas realizadas.

🔹 Extras Opcionales
🔥 Generar Facturas en PDF con iText.
🔥 Exportar datos a Excel usando Apache POI.

Estos fueron los puntos logrados que se pudieron realizar sin problemas y que funcionan correctamente.

Prueba Realizado por: Esteban Hernán Lobos Canales
Correo: esteban.hernan.lobos@gmail.com

¡Gracias por su interés en este proyecto!

