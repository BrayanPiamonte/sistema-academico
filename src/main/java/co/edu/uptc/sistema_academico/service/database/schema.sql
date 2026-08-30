CREATE DATABASE IF NOT EXISTS sistema_academico;

USE sistema_academico;

CREATE TABLE IF NOT EXISTS estudiantes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    fecha_nacimiento DATE,
    programa VARCHAR(150)
);

CREATE TABLE IF NOT EXISTS materias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    creditos INT NOT NULL,
    semestre INT NOT NULL
);

CREATE TABLE IF NOT EXISTS inscripciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    materia_id BIGINT NOT NULL,
    periodo VARCHAR(20) NOT NULL,

    CONSTRAINT fk_inscripcion_estudiante
        FOREIGN KEY (estudiante_id)
        REFERENCES estudiantes(id),

    CONSTRAINT fk_inscripcion_materia
        FOREIGN KEY (materia_id)
        REFERENCES materias(id)
);