# API REST: SII Group Library App
Esta API REST proporciona servicios relacionados con mantenimiento de registro de libros de una biblioteca. 
Está desarrollada en **Java 17** utilizando **Spring Boot 3.2.11** con una base de datos en memoria **H2**.

## Requisitos Previos
1. **Java 17**: Asegúrate de tener Java 17 instalado. Verifica con:
   ```bash
   java -version
   ```
2. **Maven**: Herramienta de construcción para Java. Verifica con:
   ```bash
   mvn -version
   ```
3. **Git** (opcional, si clonas el proyecto):
   ```bash
   git --version
   ```
## Clonar el Proyecto
Si deseas clonar el proyecto desde GitHub, ejecuta:

   ```bash
   git clone git@github.com:EpinoV/siigroup-library-app.git
   cd siigroup-library-app
   ```

## Compilación y Ejecución
1. **Compilar el Proyecto** con Maven:
   ```bash
   mvn clean install
   ```
2. **Ejecutar** la Aplicación:
   ```bash
   mvn spring-boot:run
   ```
3. **Verificar la Ejecución**: La API estará disponible en:
   > <http://localhost:8089/library-app>

## Base de Datos H2
La aplicación utiliza una base de datos en memoria H2. Puedes acceder a la consola de H2 desde:
- URL: http://localhost:8089/h2-console
- Driver: org.h2.Driver
- JDBC URL: jdbc:h2:mem:library
- Usuario: sa
- Contraseña: password

## Servicios REST y Comandos cURL
### 1. Obtener Todos los Libros existentes
### Endpoint:
    GET /library-app/api/v1/books


**Descripción**: Devuelve una lista de todos los libros disponibles.

**Comando cURL:**
   ```bash
   curl -X GET http://localhost:8089/library-app/api/v1/books -H "Content-Type: application/json"
   ```

**Respuesta Exitosa:**

   ```json
[
   {
      "id": -4,
      "title": "Cumbres borrascosas",
      "author": "Emily Bront��",
      "isbn": "9788408181804",
      "category": "NOVELA LITERARIA",
      "available": true
   },
   {
      "id": -3,
      "title": "Orgullo y prejuicio",
      "author": "Jane Austen",
      "isbn": "9788467045642",
      "category": "NOVELA LITERARIA",
      "available": true
   },
   {
      "id": -2,
      "title": "Don Quijote de la Mancha",
      "author": "Miguel de Cervantes",
      "isbn": "9788423355235",
      "category": "NOVELA LITERARIA",
      "available": true
   },
   {
      "id": -1,
      "title": "Las mil y una noches",
      "author": "Anonimo",
      "isbn": "9788423352883",
      "category": "NOVELA LITERARIA",
      "available": true
   }
]
   ```

### 2. Agregar Libro
### Endpoint:
    POST /library-app/api/v1/books

**Descripción: Guarda la información de un libro en la BD.**

**Comando cURL:**
   ```bash
curl -X POST http://localhost:8089/library-app/api/v1/books \
-H "Content-Type: application/json" \
-d '{
    "title": "El señor de los anillos, la comunidad del anillo",
    "author": "J.R.R. Tolkien",
    "isbn": "9788445009598",
    "category": "Ciencia ficción",
    "available": true
}'
   ```

**Respuesta Exitosa:**

   ```json
{
   "id": 1,
   "title": "El señor de los anillos, la comunidad del anillo",
   "author": "J.R.R. Tolkien",
   "isbn": "9788445009598",
   "category": "Ciencia ficción",
   "available": true
}
   ```

### 3. Obtener Libro por id
### Endpoint:
    GET /library-app/api/v1/books/1

**Descripción: Devuelve los datos de un libro a través del id existente.**

**Comando cURL:**
   ```
curl -X GET http://localhost:8089/library-app/api/v1/books/1 -H "Content-Type: application/json"
   ```

**Respuesta Exitosa:**

   ```json
{
   "id": 1,
   "title": "El señor de los anillos, la comunidad del anillo",
   "author": "J.R.R. Tolkien",
   "isbn": "9788445009598",
   "category": "Ciencia ficción",
   "available": true
}
   ```

### 4. Actualizar Libro
### Endpoint:
    PUT /library-app/api/v1/books/1

**Descripción: Actualiza la información de un libro existente en la BD a través de su Id.**

**Comando cURL:**
   ```bash
curl -X PUT http://localhost:8089/library-app/api/v1/books/1 \
-H "Content-Type: application/json" \
-d '{
    "title": "El señor de los anillos, la comunidad del anillo",
    "author": "J.R.R. Tolkien",
    "isbn": "9788445009599",
    "category": "Ciencia ficción",
    "available": false
}'
   ```

**Respuesta Exitosa:**

   ```json
{
   "id": 1,
   "title": "El señor de los anillos, la comunidad del anillo",
   "author": "J.R.R. Tolkien",
   "isbn": "9788445009599",
   "category": "Ciencia ficción",
   "available": false
}
   ```

### 5. Eliminar Libro por id
### Endpoint:
    DELETE /library-app/api/v1/books/1

**Descripción: Elimina toda la información de un libro a través del id existente.**

**Comando cURL:**
   ```
curl -X DELETE http://localhost:8089/library-app/api/v1/books/1 -H "Content-Type: application/json"
   ```

**Respuesta Exitosa:**

   ```json
true
   ```


## Ejecución de Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando:

   ```bash
   mvn test
   ```

## Despliegue en Producción

1. **Crear el Archivo JAR**:

   ```bash
   mvn clean package
   ```
Esto generará un archivo JAR en la carpeta `target/`.



2. **Ejecutar el JAR**:

   ```bash
   java -jar target/siigroup-library-app-0.0.1-SNAPSHOT.jar
   ```

3. **Verificar el Despliegue**: La API estará disponible en:
   > <http://localhost:8089/library-app>

## Posibles Problemas

- **CORS Bloqueado**: Si usas esta API desde un frontend local (como Angular), asegúrate de habilitar CORS en la configuración de Spring Boot.
- **H2 Console No Disponible**: Asegúrate de que la consola H2 esté habilitada en `application.properties`:

   ```properties
  spring.h2.console.enabled=true
  spring.h2.console.path=/h2-console
   ```