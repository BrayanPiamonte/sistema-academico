# Sistema Académico — Laboratorio 2

## 1. Descripción

Sistema backend desarrollado para la Fase 1 del proyecto incremental de Sistemas Distribuidos.

El sistema expone una API REST mediante HTTP para gestionar tres entidades relacionadas:

* Estudiantes
* Materias
* Inscripciones

La arquitectura utiliza una única aplicación backend y una base de datos centralizada, de acuerdo con el alcance definido para esta fase del laboratorio.

## 2. Tecnologías utilizadas

* Java
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* Hibernate
* MySQL
* Spring Validation
* Swagger / OpenAPI
* JavaFaker
* Maven

## 3. Requisitos previos

Antes de ejecutar el proyecto se requiere tener instalado:

* Java JDK
* Maven
* MySQL Server
* MySQL Workbench
* Git

## 4. Base de datos

El proyecto utiliza la base de datos:

```text
sistema_academico
```

La base contiene las tablas:

```text
estudiantes
materias
inscripciones
```

La entidad `inscripciones` relaciona estudiantes y materias mediante las claves foráneas `estudiante_id` y `materia_id`. Esto representa la relación muchos a muchos entre estudiantes y materias.

## 5. Configuración de MySQL

Crear la base de datos:

```sql
CREATE DATABASE sistema_academico;
```

Seleccionarla:

```sql
USE sistema_academico;
```

Las tablas utilizadas por la aplicación son:

```text
estudiantes
materias
inscripciones
```

La aplicación debe configurarse con las credenciales locales de MySQL mediante `application.properties`.

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_academico
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

No se debe publicar una contraseña real en el repositorio.

## 6. Ejecución del proyecto

Abrir una terminal en la carpeta raíz del proyecto:

```powershell
.\mvnw.cmd clean test
```

Si las pruebas terminan correctamente debe aparecer:

```text
BUILD SUCCESS
```

Para iniciar la aplicación:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación queda disponible en:

```text
http://localhost:8080
```

## 7. Swagger / OpenAPI

La documentación interactiva de la API está disponible en:

```text
http://localhost:8080/swagger-ui.html
```

La especificación OpenAPI está disponible en:

```text
http://localhost:8080/v3/api-docs
```

Swagger permite consultar y probar los endpoints directamente mediante `Try it out`, tal como requiere el laboratorio.

## 8. Recursos de la API

### Estudiantes

```text
GET     /estudiantes
GET     /estudiantes/{id}
POST    /estudiantes
PUT     /estudiantes/{id}
PATCH   /estudiantes/{id}
DELETE  /estudiantes/{id}
```

### Materias

```text
GET     /materias
GET     /materias/{id}
POST    /materias
PUT     /materias/{id}
PATCH   /materias/{id}
DELETE  /materias/{id}
```

### Inscripciones

```text
GET     /inscripciones
GET     /inscripciones/{id}
POST    /inscripciones
PUT     /inscripciones/{id}
PATCH   /inscripciones/{id}
DELETE  /inscripciones/{id}
```

El laboratorio exige CRUD completo para las tres entidades.

## 9. Ejemplo de creación de estudiante

Petición:

```http
POST /estudiantes
```

Cuerpo:

```json
{
  "nombre": "Ana",
  "apellido": "Rodriguez",
  "correo": "ana.rodriguez@uptc.edu.co",
  "fechaNacimiento": "2004-03-15",
  "programa": "Ingeniería de Sistemas"
}
```

Respuesta esperada:

```text
201 Created
```

## 10. Ejemplo de creación de materia

```http
POST /materias
```

```json
{
  "nombre": "Programación I",
  "codigo": "SIS101",
  "creditos": 3,
  "semestre": 1
}
```

## 11. Ejemplo de creación de inscripción

```http
POST /inscripciones
```

```json
{
  "estudianteId": 1,
  "materiaId": 1,
  "periodo": "2026-2"
}
```

## 12. Validaciones

La API valida los datos recibidos antes de almacenarlos.

Ejemplos:

* Nombre obligatorio.
* Apellido obligatorio.
* Correo obligatorio y con formato válido.
* Créditos obligatorios y mayores que cero.
* Semestre obligatorio y mayor que cero.
* Estudiante y materia obligatorios en una inscripción.

Las entradas inválidas generan respuestas HTTP `400 Bad Request`.

## 13. Códigos HTTP utilizados

```text
200 OK
```

Consulta o actualización realizada correctamente.

```text
201 Created
```

Recurso creado correctamente.

```text
204 No Content
```

Recurso eliminado correctamente sin contenido en la respuesta.

```text
400 Bad Request
```

Datos enviados inválidos.

```text
404 Not Found
```

El recurso solicitado no existe.

Estos códigos corresponden al comportamiento recomendado en la especificación del laboratorio.

## 14. Carga inicial de datos

El proyecto incluye un endpoint para realizar la población inicial:

```http
POST /datos-iniciales
```

Este mecanismo genera automáticamente los datos necesarios para cumplir el mínimo solicitado por el laboratorio:

```text
1000 estudiantes
20 materias
```

Los datos son sintéticos y se generan automáticamente utilizando JavaFaker. La guía permite explícitamente utilizar una librería de generación de datos sintéticos para esta población.

Después de ejecutar el endpoint se puede comprobar la cantidad de registros en MySQL:

```sql
SELECT COUNT(*) FROM estudiantes;
```

```sql
SELECT COUNT(*) FROM materias;
```

## 15. Verificación de la carga inicial

El proyecto debe tener como mínimo:

```text
1000 estudiantes
20 materias
```

Además, `inscripciones` permite relacionar estudiantes y materias en diferentes periodos académicos.

## 16. Estructura general del proyecto

```text
sistema-academico
│
├── src
│   └── main
│       ├── java
│       │   └── co.edu.uptc.sistema_academico
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── exception
│       │       ├── repository
│       │       └── service
│       │
│       └── resources
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## 17. Alcance de esta fase

Esta fase corresponde únicamente al backend.

No incluye:

* Frontend.
* Autenticación avanzada.
* Separación en microservicios.
* Base de datos descentralizada.

Estos elementos quedan fuera del alcance de la Fase 1 según la guía del laboratorio.
